package com.revature.repositories;

import com.revature.exceptions.RegistrationUnsuccessfulException;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

//Data access Object
    //uses conceit to connect to database and will insert and delete users
    Optional<User> result =  Optional.empty();

    public UserDAO() throws SQLException, IOException {
    }

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) {
//should reference model here
        User model = new User();

        try {
            String SQL = "SELECT * FROM ers_users WHERE ers_username = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);

            ResultSet rs = preparedStatement.executeQuery();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

            while(rs.next()){
                model.setId(rs.getInt("ers_users_id"));
                model.setUsername(rs.getString("ers_username"));
                model.setPassword(rs.getString("ers_password"));
                model.setLast(rs.getString("user_last_name"));
                model.setFirst(rs.getString("user_first_name"));
                model.setEmail(rs.getString("user_email"));
                //model.setRole();

//                model.setRole(rs.getInt("user_role_id"));

//				System.out.println(model.getUsername());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.of(model);
    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */
    public User create(User userToBeRegistered) {
        String SQL = "INSERT INTO ers_users (ers_users_id,ers_username,ers_password,user_first_name,user_last_name," +
                "user_email,user_role_id ) VALUES (?,?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            preparedStatement.setInt(1, userToBeRegistered.getId());
            preparedStatement.setString(2,userToBeRegistered.getUsername());
            preparedStatement.setString(3,userToBeRegistered.getPassword());
            preparedStatement.setString(4,userToBeRegistered.getFirst());
            preparedStatement.setString(5, userToBeRegistered.getLast());
            preparedStatement.setString(6, userToBeRegistered.getEmail());
            preparedStatement.setInt(7, 2);

            preparedStatement.executeUpdate();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

        }catch (SQLException e){
            //e.printStackTrace();
            throw new RegistrationUnsuccessfulException();
        }



        return userToBeRegistered;
    }
}
