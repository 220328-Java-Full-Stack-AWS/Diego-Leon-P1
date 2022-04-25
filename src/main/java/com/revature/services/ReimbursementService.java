package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.models.Status;

import com.revature.repositories.ReimbursementDAO;

import java.io.IOException;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.*;

import static com.revature.services.AuthService.currentUser;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 * <p>
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 * <p>
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {

    public ReimbursementService() {
        super();
    }

    /**
     * <ul>
     *     <li>Should ensure that the user is logged in as a Finance Manager</li>
     *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
     *     <li>Should ensure that the reimbursement request exists</li>
     *     <li>Must throw exception if the reimbursement request is not found</li>
     *     <li>Should persist the updated reimbursement status with resolver information</li>
     *     <li>Must throw exception if persistence is unsuccessful</li>
     * </ul>
     * <p>
     * Note: unprocessedReimbursement will have a status of PENDING, a non-zero ID and amount, and a non-null Author.
     * The Resolver should be null. Additional fields may be null.
     * After processing, the reimbursement will have its status changed to either APPROVED or DENIED.
     */


    public int process(int id, int status, int resolverId) {
        ReimbursementDAO request = new ReimbursementDAO();
        request.process(id, status, resolverId);

        return id;
    }

    /**
     * Should retrieve all reimbursements with the correct status.
     */
    public List<Reimbursement> getReimbursementsByStatus(int status) {
        ReimbursementDAO request = new ReimbursementDAO();
        List<Reimbursement> list;
        list = request.getByStatus(status);

        return list;
    }


    public Reimbursement request(Reimbursement requestTobeSubmitted) throws SQLException, IOException {
        ReimbursementDAO request = new ReimbursementDAO();
//
//        Scanner myObj = new Scanner(System.in);
//
//
//        System.out.println("Enter Amount: ");
//        requestTobeSubmitted.setAmount(Double.parseDouble(myObj.nextLine()));
//
//        System.out.println("Enter a description: ");
//        requestTobeSubmitted.setDescription(myObj.nextLine());
//
//        System.out.println("Enter receipt number: ");
//        requestTobeSubmitted.setReceipt(myObj.nextLine());
//
//
//        Boolean valid = false;
//        while (!valid) {
//            System.out.println("Enter type of Reimbusrsement: \n" +
//                    "1. Lodging\n" +
//                    "2. Travel\n" +
//                    "3. Food");
//
//            int type = myObj.nextInt();
//            if (type == 1) {
//                requestTobeSubmitted.setType(1);
//                valid = true;
//            } else if (type == 2) {
//                requestTobeSubmitted.setType(2);
//                valid = true;
//            } else if (type == 3) {
//                requestTobeSubmitted.setType(3);
//                valid = true;
//            } else {
//                System.out.println("Wrong type selected. Please try again.");
//            }
//        }
//        requestTobeSubmitted.setAuthor(currentUser.getId());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp);

        requestTobeSubmitted.setSubmitted(timestamp);


        return request.createRequest(requestTobeSubmitted);
    }

    public int cancelByID(int reimbursementId) throws SQLException, IOException {
        ReimbursementDAO request = new ReimbursementDAO();
        request.cancelById(reimbursementId);

        return reimbursementId;
    }

    public int requestToBeEditted(int id) throws SQLException, IOException {
        ReimbursementDAO request = new ReimbursementDAO();
        Reimbursement model = new Reimbursement();
        Scanner myObj = new Scanner(System.in);


        System.out.println("Enter Amount: ");
        model.setAmount(Double.parseDouble(myObj.nextLine()));

        System.out.println("Enter a description: ");
        model.setDescription(myObj.nextLine());

        System.out.println("Enter receipt number: ");
        model.setReceipt(myObj.nextLine());


        Boolean valid = false;
        while (!valid) {
            System.out.println("Enter type of Reimbusrsement: \n" +
                    "1. Lodging\n" +
                    "2. Travel\n" +
                    "3. Food");

            int type = myObj.nextInt();
            if (type == 1) {
                model.setType(1);
                valid = true;
            } else if (type == 2) {
                model.setType(2);
                valid = true;
            } else if (type == 3) {
                model.setType(3);
                valid = true;
            } else {
                System.out.println("Wrong type selected. Please try again.");
            }
        }
        model.setAuthor(currentUser.getId());

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        model.setSubmitted(timestamp);

        request.requestToBeEditted(model, id);
        return id;
    }

    public List<Reimbursement> getByUserId(int id) {
        ReimbursementDAO request = new ReimbursementDAO();
        List<Reimbursement> list;

        list = request.getByUserId(id);

        return list;
    }

}


