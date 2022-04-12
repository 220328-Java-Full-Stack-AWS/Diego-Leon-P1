package com.revature.util;

import com.revature.Driver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <p>This ConnectionFactory class follows the Singleton Design Pattern and facilitates obtaining a connection to a Database for the ERS application.</p>
 * <p>Following the Singleton Design Pattern, the provided Constructor is private, and you obtain an instance via the {@link ConnectionFactory#getInstance()} method.</p>
 */
public class ConnectionFactory {

    private static Connection connection;
    //removed super();
    private ConnectionFactory() {

    }

    /**
     * <p>This method follows the Singleton Design Pattern to restrict this class to only having 1 instance.</p>
     * <p>It is invoked via:</p>
     *
     * {@code ConnectionFactory.getInstance()}
     */
    public static Connection getConnection() throws SQLException, IOException {
        if(connection == null) {
            connection = connect();
        }

        return connection;
    }

    /**
     * <p>The {@link ConnectionFactory#connect()} method is responsible for leveraging a specific Database Driver to obtain an instance of the {@link java.sql.Connection} interface.</p>
     * <p>Typically, this is accomplished via the use of the {@link java.sql.DriverManager} class.</p>
     */
    static Connection connect() throws IOException, SQLException {
        //creating jdbc url to connect to db

        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream input = loader.getResourceAsStream("application.properties");
        props.load(input);
//
        String connectionString = "jdbc:postgresql://" +
                props.getProperty("hostname") + ":" +
                props.getProperty("port") + "/" +
                props.getProperty("dbname");

        String username = props.getProperty("username");
        String password = props.getProperty("password");

        connection = DriverManager.getConnection(connectionString, username, password);
//
//        try {
//            Connection connection = DriverManager.getConnection(url, props);
//            System.out.println("Connection to PostgresSQL successful.");
//        } catch (SQLException e) {
//            System.out.println("Error connecting to PostgresSQL.");
//            e.printStackTrace();
//        }

        //rreccomended that each crud operation be a class each




        return connection;
    }

}
