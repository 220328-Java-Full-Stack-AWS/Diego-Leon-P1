package com.revature.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.util.ConnectionFactory;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

//	Connection connection = ConnectionFactory.getConnection();

	public UserService() throws SQLException, IOException {
		super();
	}

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
	 * @return
	 */
	public Optional<Object> getByUsername(String username) throws SQLException, IOException {
		UserDAO user = new UserDAO();
		Optional<User> model = user.getByUsername(username);

		System.out.println("ID: "+ model.get().getId() + "\nUsername: : " + model.get().getUsername() + "\nPassword: " + model.get().getPassword()
				+ "\nName: " + model.get().getFirst() + " " + model.get().getLast() + "\nEmail: " + model.get().getEmail()
				+ "\nRole: " + model.get().getRole());
		return Optional.of(model);
	}

	public Optional<Object> create(User userToBeRegistered) throws SQLException, IOException {
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


			//Optional<User> model = Optional.ofNullable(user.create(userToBeRegistered));

			//return Optional.empty();
		//return Optional.of(user.create(userToBeRegistered));
		return Optional.of(user.create(userToBeRegistered));
	}
}
