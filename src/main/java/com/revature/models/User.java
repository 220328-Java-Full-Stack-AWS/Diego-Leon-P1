package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This concrete User class can include additional fields that can be used for
 * extended functionality of the ERS application.
 *
 * Example fields:
 * <ul>
 *     <li>First Name</li>
 *     <li>Last Name</li>
 *     <li>Email</li>
 *     <li>Phone Number</li>
 *     <li>Address</li>
 * </ul>
 *
 */
@JsonIgnoreProperties  //This tells the Jackson object mapper that if there are properties in this class missing from the JSON, it will simpley ignore those
public class User extends AbstractUser {

    public User() {
        super();
    }

    /**
     * This includes the minimum parameters needed for the {@link com.revature.models.AbstractUser} class.
     * If other fields are needed, please create additional constructors.
     */
    public User(int id, String username, String password, String first, String last, String email, int role) {
        super(id,username,password,first,last, email, role);
    }
}
