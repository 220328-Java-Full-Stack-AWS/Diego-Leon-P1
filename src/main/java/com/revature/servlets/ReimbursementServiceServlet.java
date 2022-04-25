package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

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
    Integer ID;
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

        int choice = Integer.parseInt(req.getHeader("case"));
        switch (choice) {
            case 1:
                list = service.getByUserId(Integer.parseInt(req.getHeader("ID")));
                String json = mapper.writeValueAsString(list);
                resp.setContentType("application/json");
                resp.getWriter().print(json);
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
        // super.doPost(req, resp);

        amount = Double.parseDouble(req.getHeader("amount"));
        description = req.getHeader("description");
        receipt = req.getHeader("receipt");
        author = Integer.parseInt(req.getHeader("author"));
        typeId = Integer.parseInt(req.getHeader("typeId"));

        model.setAmount(amount);
        model.setDescription(description);
        model.setReceipt(receipt);
        model.setAuthor(author);
        model.setType(typeId);

        try {
            service.request(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doDelete(req, resp);
        ID = Integer.parseInt(req.getHeader("ID"));
        try {
            service.cancelByID(ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
