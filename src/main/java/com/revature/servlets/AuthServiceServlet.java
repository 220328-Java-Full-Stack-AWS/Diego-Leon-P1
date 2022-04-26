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
    UserService userService = new UserService();
    User user = new User();
    User userCheck = new User();
    String first;
    String last;
    String email;
    String password;
    String user_name;
    private ObjectMapper mapper = new ObjectMapper();

    public AuthServiceServlet() throws SQLException, IOException {
    }

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

                user = mapper.readValue(req.getInputStream(), User.class);
                resp.setStatus(201);
                try {
                    authService.register(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 2:


                 user = mapper.readValue(req.getInputStream(), User.class);
                try {
                    userCheck = userService.getByUsername(user.getUsername());

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                System.out.println("in case 2");
                if(userCheck != null && userCheck.getPassword().equals(user.getPassword())) {
                    resp.setStatus(200);
                    resp.getWriter().print(new ObjectMapper().writeValueAsString(userCheck));
                    resp.setHeader("access-control-expose-headers", "authToken");
                    resp.setHeader("authToken", String.valueOf(userCheck.getId()));


                } else {
                    resp.setStatus(401);
                }
                break;
            default:
                resp.setStatus(400);
                break;
        }

    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }


}
