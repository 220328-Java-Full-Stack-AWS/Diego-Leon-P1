package com.revature.repositories;


import com.revature.models.Reimbursement;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) {
        Reimbursement request = new Reimbursement();

        try {
            String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

            while (rs.next()) {
                request.setId(rs.getInt("reimb_id"));
                request.setAmount(rs.getDouble("reimb_amount"));
                request.setSubmitted(rs.getTimestamp("reimb_submitted"));
                request.setResolved(rs.getTimestamp("reimb_resolved"));
                request.setDescription(rs.getString("reimb_description"));
                request.setReceipt(rs.getString("reimb_receipt"));
                request.setAuthor(rs.getInt("reimb_author"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ID: " + request.getId() + "\nAmount: " + request.getAmount() + "\nDate Submitted: "
                + request.getSubmitted() + "\nDate Resolved: " + request.getResolved() + "\nDescription: " + request.getDescription()
                + "\nReceipt Number: " + request.getReceipt() + "\nSubmitted by: " + request.getAuthor());

        return Optional.empty();
    }

    ////GEt by userID
    public List<Reimbursement> getByUserId(int id) {
        List<Reimbursement> list = new LinkedList<>();

        try {
            String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

            while (rs.next()) {
                Reimbursement model = new Reimbursement();
                model.setId(rs.getInt("reimb_id"));
                model.setAmount(rs.getDouble("reimb_amount"));
                model.setSubmitted(rs.getTimestamp("reimb_submitted"));
                model.setResolved(rs.getTimestamp("reimb_resolved"));
                model.setDescription(rs.getString("reimb_description"));
                model.setReceipt(rs.getString("reimb_receipt"));
                model.setAuthor(rs.getInt("reimb_author"));
                model.setStatus(rs.getInt("reimb_status_id"));

                list.add(model);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return list;
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with
     * the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(int status) {
        List<Reimbursement> list = new LinkedList<>();

        try {
            String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, status);

            ResultSet rs = preparedStatement.executeQuery();
            //next has to be called. it is a boolean. if there is something there it returns true and false if not

            while (rs.next()) {
                Reimbursement model = new Reimbursement();
                model.setId(rs.getInt("reimb_id"));
                model.setAmount(rs.getDouble("reimb_amount"));
                model.setSubmitted(rs.getTimestamp("reimb_submitted"));
                model.setResolved(rs.getTimestamp("reimb_resolved"));
                model.setDescription(rs.getString("reimb_description"));
                model.setReceipt(rs.getString("reimb_receipt"));
                model.setAuthor(rs.getInt("reimb_author"));
                model.setStatus(rs.getInt("reimb_status_id"));

                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {

        return null;
    }

    public Reimbursement createRequest(Reimbursement requestTobeSubmitted) {
        String SQL = "INSERT INTO ers_reimbursement (reimb_amount,reimb_submitted,reimb_description,reimb_receipt,"
                + " reimb_author,reimb_type_id ) VALUES (?,?, ?, ?, ?,? )";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            preparedStatement.setDouble(1, requestTobeSubmitted.getAmount());
            preparedStatement.setTimestamp(2, requestTobeSubmitted.getSubmitted());
            preparedStatement.setString(3, requestTobeSubmitted.getDescription());
            preparedStatement.setString(4, requestTobeSubmitted.getReceipt());
            preparedStatement.setInt(5, requestTobeSubmitted.getAuthor());
            preparedStatement.setInt(6, requestTobeSubmitted.getType());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return requestTobeSubmitted;
    }


    public Reimbursement requestToBeEditted(Reimbursement requestTobeEditted, int id) {
        String SQL = "UPDATE  ers_reimbursement SET reimb_amount =? ,reimb_submitted = ?,reimb_description =?,reimb_receipt =?,"
                + " reimb_author = ?,reimb_type_id =? WHERE reimb_id = ?";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            preparedStatement.setDouble(1, requestTobeEditted.getAmount());
            preparedStatement.setTimestamp(2, requestTobeEditted.getSubmitted());
            preparedStatement.setString(3, requestTobeEditted.getDescription());
            preparedStatement.setString(4, requestTobeEditted.getReceipt());
            preparedStatement.setInt(5, requestTobeEditted.getAuthor());
            preparedStatement.setInt(6, requestTobeEditted.getType());
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return requestTobeEditted;
    }

    public int cancelById(int requestTobeCancelled) {
        String SQL = "DELETE from ers_reimbursement where reimb_id = ?";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            preparedStatement.setInt(1, requestTobeCancelled);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return requestTobeCancelled;
    }

    public int process(int id, int status, int userId) {
            String SQL = "UPDATE  ers_reimbursement SET reimb_status_id = ?, reimb_resolver = ?  WHERE reimb_id = ?";

        try {

            PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(SQL);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, id);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return id;
    }

}
