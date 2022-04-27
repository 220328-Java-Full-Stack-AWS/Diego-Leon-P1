package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;
import org.postgresql.gss.GSSOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementServiceServlet extends HttpServlet {
    private ReimbursementService service =new ReimbursementService();
    private ObjectMapper mapper;
    List<Reimbursement> list;
    Reimbursement model = new Reimbursement();



    @Override
    public void init() throws ServletException {

        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();

    }


    /**
     * doGet Method that returns a List of pending reimbursements by User ID
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("in REIMB doGEt");
        System.out.println(("authToken"));
        int choice = Integer.parseInt(req.getHeader("case"));
        switch (choice) {
            case 1:
                System.out.println("in case 1");
                System.out.println(Integer.parseInt(req.getHeader("authToken")));
                list = service.getByUserId(Integer.parseInt(req.getHeader("authToken")));
                System.out.println(list);
                String json = mapper.writeValueAsString(list);
                resp.setContentType("application/json");
                resp.getWriter().write(json);
                System.out.println("before status");
                resp.setStatus(200);
                break;
            case 2:
                list = service.getReimbursementsByStatus(Integer.parseInt(req.getHeader("status")));
                String json2 = mapper.writeValueAsString(list);
                resp.setContentType("application/json");
                resp.getWriter().write(json2);
                resp.setStatus(200);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("before Mapper");
        model = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);
        model.setAuthor(Integer.parseInt(req.getHeader("authToken")));
        try {

            service.request(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
        System.out.println("in PUT");
        int choice = Integer.parseInt(req.getHeader("case"));
        switch (choice) {
            case 1:
                System.out.println("IN doPut");
                System.out.println((req.getHeader("reimbursement")));
                model = new ObjectMapper().readValue(req.getInputStream(), Reimbursement.class);

                model.setId((Integer.parseInt(req.getHeader("reimbursement"))));
                model.setAuthor((Integer.parseInt(req.getHeader("authToken"))));

                System.out.println(model);
                try {
                    service.requestToBeEditted(model.getId(), model);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.setStatus(200);
                break;
            case 2:
                System.out.println("update");
                int reimbId, status, resolver;

                resolver = (Integer.parseInt(req.getHeader("authToken")));
                status = (Integer.parseInt(req.getHeader("status")));
                reimbId = (Integer.parseInt(req.getHeader("reimbursement")));
                System.out.println(resolver);
                System.out.println(status);
                System.out.println(reimbId);
                service.process(reimbId, status, resolver);
                resp.setStatus(200);
                break;
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       int ID = Integer.parseInt(req.getHeader("reimbursement"));
        try {
            service.cancelByID(ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(200);
    }

}
