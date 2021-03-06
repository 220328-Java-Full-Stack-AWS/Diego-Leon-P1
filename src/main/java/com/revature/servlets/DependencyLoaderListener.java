package com.revature.servlets;

import com.revature.util.ConnectionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class DependencyLoaderListener implements ServletContextListener {
    Connection conn;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            conn = ConnectionFactory.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Context is Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionFactory.close();
        System.out.println("Context is destroyed");

    }
}