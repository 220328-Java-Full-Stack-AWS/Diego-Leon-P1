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

public class UserServiceServlet extends HttpServlet {
    private UserService service;
    private ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        try {
            this.service = new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User model = null;
        try {
            model = service.getByUsername(req.getHeader("user_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String json = mapper.writeValueAsString(model);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);

    }

}
