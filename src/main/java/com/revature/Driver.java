package com.revature;

//import jdk.jfr.internal.consumer.FinishedStream;
//import org.postgresql.core.ConnectionFactory;
//
//import javax.annotation.processing.SupportedAnnotationTypes;
//import java.sql.Connection;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.UserService;
import com.revature.util.ConnectionFactory;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;

public class Driver {

    private static boolean running = true;
    private static boolean loggedIn = false;

    public static void main(String[] args) throws SQLException, IOException {
        
        AuthService user = null;
        User user1;

        while (running) {
            if (!loggedIn) {
                System.out.println("What would you like to do: " + "\n" +
                        "Type to 1 Log In" + "\n" +
                        "Type 2 to Register");

                Scanner choice = new Scanner(System.in);
                int userChoice = Integer.parseInt(choice.nextLine());

                if (userChoice == 1) {

                    //There is an issue where if there is white space after the username it will not recognize it
                    user = new AuthService();

                    System.out.println("Log In Page");
                    String user_name;
                    String password;

                    Scanner myObj = new Scanner(System.in);  // Create a Scanner object

                    System.out.println("Enter user name: ");
                    user_name = (myObj.nextLine());


                    System.out.println("Enter password: ");
                    password = (myObj.nextLine());

                    if (user.login(user_name, password)) {
                        loggedIn = true;
                    } else {
                        System.out.println("Username and Password combination do not math." + "\n" +
                                "Please try again or register if you're a new user.");
                    }


                } else if (userChoice == 2) {
                    System.out.println("Registration Page");

                    user = new AuthService();
                     user1 = new User();
                    user.register(user1);

                }


            } else if (loggedIn){
                System.out.println("Welcome to the ERS Application, " +
                        user.exampleRetrieveCurrentUser().get().getFirst() + " " +
                        user.exampleRetrieveCurrentUser().get().getLast()) ;
                System.out.println("What would you like to do: " + "\n" +
                        "1. Submit Reimbursement Request" + "\n" +
                        "2. Cancel Pending Reimbursement Request" + "\n" +
                        "3. View Past, Pending and Completed Reimbursement Requests" + "\n" +
                        "4. Edit Pending Reimbursement Request" + "\n");
                Scanner choice = new Scanner(System.in);
                int userChoice = Integer.parseInt(choice.nextLine());
            }
        }

    }
}
