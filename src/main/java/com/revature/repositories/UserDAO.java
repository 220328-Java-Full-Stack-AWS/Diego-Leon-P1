package com.revature.repositories;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;



public class UserDAO {


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

                // model.setRole(rs.getInt("user_role_id"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.of(model);
    }

////    //get by ID
////
//    public Optional<User> getByID(int id) {
//        //should reference model here
//        User model = new User();
//
//        try {
//            String SQL = "SELECT * FROM ers_users WHERE ers_users_id = ?";
//            Connection connection = ConnectionFactory.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setInt(1, id);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            //next has to be called. it is a boolean. if there is something there it returns true and false if not
//
//            while(rs.next()){
//                model.setId(rs.getInt("ers_users_id"));
//                model.setUsername(rs.getString("ers_username"));
//                model.setPassword(rs.getString("ers_password"));
//                model.setLast(rs.getString("user_last_name"));
//                model.setFirst(rs.getString("user_first_name"));
//                model.setEmail(rs.getString("user_email"));
//                //model.setRole();
//
//                // model.setRole(rs.getInt("user_role_id"));
//
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        return Optional.of(model);
//    }

    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */
    public User register(User userToBeRegistered) {
        String SQL = "INSERT INTO ers_users (ers_username,ers_password,user_first_name,user_last_name," +
                "user_email,user_role_id ) VALUES (?,?,?,?,?,?)";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            //ID is not set since it is set to Serial in the DB
            preparedStatement.setString(1,userToBeRegistered.getUsername());
            preparedStatement.setString(2,userToBeRegistered.getPassword());
            preparedStatement.setString(3,userToBeRegistered.getFirst());
            preparedStatement.setString(4, userToBeRegistered.getLast());
            preparedStatement.setString(5, userToBeRegistered.getEmail());
            preparedStatement.setInt(6, 2);


            preparedStatement.executeUpdate();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

        }catch (SQLException e){
            e.printStackTrace();
            //throw new RegistrationUnsuccessfulException();
        }



        return userToBeRegistered;
    }


    public boolean login(String username, String password, User currentUser) throws SQLException, IOException {

        String SQL = "SELECT * FROM ers_users WHERE ers_username = ? ";
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL);
        preparedStatement.setString(1, username);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){
            currentUser.setUsername(rs.getString("ers_username"));
            currentUser.setPassword(rs.getString("ers_password"));
            currentUser.setId(rs.getInt("ers_users_id"));
            currentUser.setFirst(rs.getString("user_first_name"));
            currentUser.setLast(rs.getString("user_last_name"));
            currentUser.setEmail(rs.getString("user_email"));
        }

        return false;
    }



}
