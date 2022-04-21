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

	UserDAO user;

	public UserService() throws SQLException, IOException {
		super();
		user = new UserDAO();
	}

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
	 * @return
	 */
	public User getByUsername(String username) throws SQLException, IOException {

		User model = user.getByUsername(username);

		System.out.println("ID: "+ model.getId() + "\nUsername: : " + model.getUsername() + "\nPassword: " + model.getPassword()
				+ "\nName: " + model.getFirst() + " " + model.getLast() + "\nEmail: " + model.getEmail()
				+ "\nRole: " + model.getRole());

		return model;
	}

//	public Optional<Object> getByID(int id) throws SQLException, IOException {
//		//UserDAO user = new UserDAO();
//		Optional<User> model = this.user.getByID(id);
//
//		System.out.println("ID: "+ model.get().getId() + "\nUsername: : " + model.get().getUsername() + "\nPassword: " + model.get().getPassword()
//				+ "\nName: " + model.get().getFirst() + " " + model.get().getLast() + "\nEmail: " + model.get().getEmail()
//				+ "\nRole: " + model.get().getRole());
//
//		return Optional.of(model);
//	}

}
