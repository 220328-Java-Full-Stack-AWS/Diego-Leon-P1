package com.revature;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.services.AuthService;
import com.revature.services.ReimbursementService;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Driver {

    private static boolean running = true;
    private static boolean loggedIn = false;
    private static boolean justRegistered = false;
    private static AuthService user = null;
    private static User user1;
    private static ReimbursementService reimbursement = null;
    private static Reimbursement reimbursement1;

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        while (running) {
            if (!loggedIn) {
                System.out.println("What would you like to do: " + "\n" +
                        "Type to 1 Log In" + "\n" +
                        "Type 2 to Register" + "\n" +
                        "Type 3 to Exit");

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
                    user.login(user1.getUsername(), user1.getPassword());
                    loggedIn = true;
                    justRegistered = true;

                } else if (userChoice == 3) {
                    System.out.println("Goodbye.");
                    running = false;
                }


            } else if ((loggedIn) && (user.retrieveCurrentUser().getRole() == 1)) {
                System.out.println("Welcome to the ERS Application(Manager), " +
                        user.retrieveCurrentUser().getFirst() + " " +
                        user.retrieveCurrentUser().getLast() + "." +
                        "Your username is: " + user.retrieveCurrentUser().getUsername());
                System.out.println("What would you like to do: " + "\n" +
                        "1. Approve Reimbursement Request" + "\n" +
                        "2. Deny Reimbursement Request" + "\n" +
                        "3. Filter Reimbursement Requests by Status" + "\n" +
                        "4. Assign Roles" + "\n" +
                        "5. Log off");

                Scanner choice = new Scanner(System.in);
                int userChoice = Integer.parseInt(choice.nextLine());

                if (userChoice == 1) {
                    System.out.println("Approval Page\n\n");

                    reimbursement = new ReimbursementService();
                    List<Reimbursement> list = reimbursement.getReimbursementsByStatus(1);
                    for (Reimbursement each :
                            list) {
                        System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: "
                                + each.getDescription()
                                + "\nReceipt Number: " + each.getReceipt());
                    }
                    System.out.println("\nType the reimbursement ID to approve.");
                    Scanner choice1 = new Scanner(System.in);
                    int userChoice1 = Integer.parseInt(choice1.nextLine());
                    reimbursement.process(userChoice1, 2, user.retrieveCurrentUser().getId());
                } else if (userChoice == 2) {
                    System.out.println("Denial Page\n\n");
                    reimbursement = new ReimbursementService();

                    List<Reimbursement> list = reimbursement.getReimbursementsByStatus(1);
                    for (Reimbursement each :
                            list) {
                        System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: "
                                + each.getDescription()
                                + "\nReceipt Number: " + each.getReceipt());
                    }
                    System.out.println("\nType the reimbursement ID to deny.");
                    Scanner choice1 = new Scanner(System.in);
                    int userChoice1 = Integer.parseInt(choice1.nextLine());
                    reimbursement.process(userChoice1, 3,user.retrieveCurrentUser().getId());
                } else if (userChoice == 3) {
                    System.out.println("Filter Results\n");
                    System.out.println("Filter by: " + "\n" +
                            "1. Pending" + "\n" +
                            "2. Approved" + "\n" +
                            "3. Denied");
                     choice = new Scanner(System.in);
                    int userChoice1 = Integer.parseInt(choice.nextLine());
                            reimbursement = new ReimbursementService();

                            if (userChoice1 == 1){
                                List<Reimbursement> list = reimbursement.getReimbursementsByStatus(userChoice1);
                                for (Reimbursement each :
                                        list) {
//                                        System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
//                                                + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
//                                                + "\nReceipt Number: " + each.getReceipt());


                                    System.out.println("ID: "  + "  Amount: " + "   Date Submitted: "
                                            +  "        Date Resolved: " + " Description: " +
                                             " Receipt Number: ");
                                    System.out.println(each.getId() + "     " + each.getAmount() + "    "
                                            + each.getSubmitted() + "   " + each.getResolved() + "               " + each.getDescription()
                                            + "                " + each.getReceipt());

                                }
                            }else if (userChoice1 == 2){
                                List<Reimbursement> list = reimbursement.getReimbursementsByStatus(userChoice1);
                                for (Reimbursement each :
                                        list) {
                                    System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                            + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
                                            + "\nReceipt Number: " + each.getReceipt());
                                }
                            }else if(userChoice1 ==3){
                                    List<Reimbursement> list = reimbursement.getReimbursementsByStatus(userChoice1);
                                    for (Reimbursement each :
                                            list) {
                                        System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                                + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
                                                + "\nReceipt Number: " + each.getReceipt());
                                }
                            }else{
                                System.out.println("No such choice, choose again.");
                            }

                } else if (userChoice == 4) {
                    System.out.println("Role Assigment Page.");

                } else if (userChoice == 5) {
                    System.out.println("Goodbye.");
                    loggedIn = false;
                }
            } else if (loggedIn) {
                System.out.println("Welcome to the ERS Application, " +
                        user.retrieveCurrentUser().getFirst() + " " +
                        user.retrieveCurrentUser().getLast() + ".");
                if (justRegistered == true){
                    System.out.println("\nYour username is: " + user.retrieveCurrentUser().getUsername());
                }
                System.out.println("What would you like to do: " + "\n" +
                        "1. Submit Reimbursement Request" + "\n" +
                        "2. Cancel Pending Reimbursement Request" + "\n" +
                        "3. View Past, Pending and Completed Reimbursement Requests" + "\n" +
                        "4. Edit Pending Reimbursement Request" + "\n" +
                        "5. Log Off");
                Scanner choice = new Scanner(System.in);
                int userChoice = Integer.parseInt(choice.nextLine());

                if (userChoice == 1) {
                    System.out.println("Submission Page");
                    reimbursement = new ReimbursementService();
                    reimbursement1 = new Reimbursement();
                    reimbursement.request(reimbursement1);
                } else if (userChoice == 2) {
                    System.out.println("Cancellation Page");
                    System.out.println(user.retrieveCurrentUser().getId());
                    reimbursement = new ReimbursementService();
                    List<Reimbursement> list = reimbursement.getByUserId(user.retrieveCurrentUser().getId());
                    for (Reimbursement each :
                            list) {
                        if (each.getStatus() == 1) {
                            System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                    + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
                                    + "\nReceipt Number: " + each.getReceipt());
                        }
                    }

                    System.out.println("Type Reimbursement Id TO CANCEL: ");
                    Scanner choice1 = new Scanner(System.in);
                    int userChoice1 = Integer.parseInt(choice1.nextLine());

                    reimbursement.cancelByID(userChoice1);
                } else if (userChoice == 3) {
                    System.out.println("Submission History");

                    List<Reimbursement> list = reimbursement.getByUserId(user.retrieveCurrentUser()
                            .getId());
                    for (Reimbursement each :
                            list) {
                        System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
                                + "\nReceipt Number: " + each.getReceipt());
                    }
                } else if (userChoice == 4) {
                    System.out.println("Editing Page");
                    System.out.println("Submission History");
                    reimbursement = new ReimbursementService();
                    List<Reimbursement> list = reimbursement.getByUserId(user.retrieveCurrentUser()
                            .getId());
                    for (Reimbursement each :
                            list) {
                        if (each.getStatus() == 1) {
                            System.out.println("ID: " + each.getId() + "\nAmount: " + each.getAmount() + "\nDate Submitted: "
                                    + each.getSubmitted() + "\nDate Resolved: " + each.getResolved() + "\nDescription: " + each.getDescription()
                                    + "\nReceipt Number: " + each.getReceipt());
                        }
                    }
                    System.out.println("Type Reimbursement Id TO Edit: ");
                    Scanner choice1 = new Scanner(System.in);
                    int userChoice1 = Integer.parseInt(choice1.nextLine());
                    reimbursement = new ReimbursementService();
                    reimbursement.requestToBeEditted(userChoice1);
                } else if (userChoice == 5) {
                    System.out.println("Goodbye.");
                    loggedIn = false;
                }
            }

        }

    }
}
