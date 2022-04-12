package com.revature;

//import jdk.jfr.internal.consumer.FinishedStream;
//import org.postgresql.core.ConnectionFactory;
//
//import javax.annotation.processing.SupportedAnnotationTypes;
//import java.sql.Connection;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    private static boolean running = true;

    public static void main(String[] args) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            System.out.println("Connection to PostgresSQL successful.");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        while (running) {
//
//            System.out.println("What would you like to do: " + "\n"
//                    "Type to 1 Log In" + "\n" +
//                    "Type 2 to Register");
//
//            Scanner choice = new Scanner(System.in);
//             int userChoice = Integer.parseInt(choice.nextLine());
//
//            if (userChoice == 1) {
//                System.out.println("Log In Page");
//                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//                System.out.println("Enter username: ");
//
//                String userName = myObj.nextLine();  // Read user input
//                System.out.println("Username is: " + userName);  // Output user input
//
//                System.out.println("Enter Password: ");
//                String password = myObj.nextLine();
//                System.out.println("Username is: " + password);  // Output user input
//            } else if (userChoice == 2){
//                System.out.println("Registration Page");
//                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//                System.out.println("Enter username: ");
//
//
//
//
//                String userName = myObj.nextLine();  // Read user input
//                System.out.println("Username is: " + userName);  // Output user input
//
//                System.out.println("Enter Password: ");
//                String password = myObj.nextLine();
//                System.out.println("Username is: " + password);  // Output user input
//            }
//
//
//        }

    }
}
