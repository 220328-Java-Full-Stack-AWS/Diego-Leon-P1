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
    String description, receipt;
    Double amount;
    Integer author, typeId;
    String userName;
    Reimbursement model = new Reimbursement();
    UserService userService;


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
        int choice = Integer.parseInt(req.getHeader("case"));
        switch (choice) {
            case 1:
                System.out.println("in case 1");
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
                resp.getWriter().print(json2);
                resp.setStatus(200);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        model = mapper.readValue(req.getInputStream(), Reimbursement.class);
        model.setAuthor(Integer.parseInt(req.getHeader("authToken")));

        try {
            resp.setStatus(201);
            System.out.println(model);
            service.request(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPut(req, resp);
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
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
        System.out.println("IN DELETE");
       int ID = Integer.parseInt(req.getHeader("reimbursement"));
        try {
            service.cancelByID(ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setStatus(200);
    }

}
