package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

/**
 * The AuthService should handle login and registration for the ERS application.
 *
 * {@code login} and {@code register} are the minimum methods required; however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Retrieve Currently Logged-in User</li>
 *     <li>Change Password</li>
 *     <li>Logout</li>
 * </ul>
 */
public class AuthService {
    // might be able to use this to get curren tuser
    private static User currentUser = new User();

    /**
     * <ul>
     *     <li>Needs to check for existing users with username/email provided.</li>
     *     <li>Must throw exception if user does not exist.</li>
     *     <li>Must compare password provided and stored password for that user.</li>
     *     <li>Should throw exception if the passwords do not match.</li>
     *     <li>Must return user object if the user logs in successfully.</li>
     * </ul>
     */
    public User login(String username, String password) throws SQLException, IOException {

        //User model = new User();

        String SQL = "SELECT * FROM ers_users WHERE ers_username = ? ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);
        //preparedStatement.setString(2, password);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            currentUser.setUsername(rs.getString("ers_username"));
            currentUser.setPassword(rs.getString("ers_password"));
            currentUser.setId(rs.getInt("ers_users_id"));
            currentUser.setFirst(rs.getString("user_first_name"));
            currentUser.setLast(rs.getString("user_last_name"));
            currentUser.setEmail(rs.getString("user_email"));
        }

        if ((username.equals(currentUser.getUsername()) && (password.equals(currentUser.getPassword())))){
            System.out.println("User Logged in Successfully.");

//            return model;
        }

        return null;
    }

    /**
     * <ul>
     *     <li>Should ensure that the username/email provided is unique.</li>
     *     <li>Must throw exception if the username/email is not unique.</li>
     *     <li>Should persist the user object upon successful registration.</li>
     *     <li>Must throw exception if registration is unsuccessful.</li>
     *     <li>Must return user object if the user registers successfully.</li>
     *     <li>Must throw exception if provided user has a non-zero ID</li>
     * </ul>
     *
     * Note: userToBeRegistered will have an id=0, additional fields may be null.
     * After registration, the id will be a positive integer.
     */
    public User register(User userToBeRegistered) throws SQLException, IOException {
        UserDAO user = new UserDAO();

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Enter First name: ");
        userToBeRegistered.setFirst(myObj.nextLine());// Read user input

        System.out.println("Enter Last name: ");
        userToBeRegistered.setLast(myObj.nextLine());

        System.out.println("Enter email: ");
        userToBeRegistered.setEmail(myObj.nextLine()); // Read user input

        System.out.println("Enter password: ");
        userToBeRegistered.setPassword(myObj.nextLine());

        Random rand = new Random();
        int id = (int)(1000 + (Math.random() * 10000));
        String userName = userToBeRegistered.getFirst() + userToBeRegistered.getLast() + (int)(1000 + (Math.random() * 10000));
        userToBeRegistered.setId(id);
        userToBeRegistered.setUsername(userName);

//			System.out.println("Enter role: ");
//			userToBeRegistered.setRole(myObj.nextLine());

        return user.create(userToBeRegistered);


    }

    /**
     * This is an example method signature for retrieving the currently logged-in user.
     * It leverages the Optional type which is a useful interface to handle the
     * possibility of a user being unavailable.
     */
    public Optional<User> exampleRetrieveCurrentUser() {
        System.out.println(currentUser);
        return Optional.empty();
    }
}
