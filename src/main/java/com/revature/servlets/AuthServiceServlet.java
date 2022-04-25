package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthServiceServlet extends HttpServlet {

    AuthService authService = new AuthService();
    User user = new User();
    String first;
    String last;
    String email;
    String password;
    String user_name;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //Retrieves current user but not quite sure how it will be implemented just yet

//        try {
//            user = authService.retrieveCurrentUser();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String json = mapper.writeValueAsString(user);
//        resp.setContentType("application/json");
//        resp.getWriter().print(json);
        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int choice = Integer.parseInt(req.getHeader("case"));
        switch (choice) {
            case 1:

                ObjectMapper mapper = new ObjectMapper();
                user = mapper.readValue(req.getInputStream(), User.class);
                resp.setStatus(201);
                System.out.println(user);
                try {
                    authService.register(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                 user_name = req.getHeader("user_name");
                 password = req.getHeader("password");

                try {
                    authService.login(user_name,password);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }


}
