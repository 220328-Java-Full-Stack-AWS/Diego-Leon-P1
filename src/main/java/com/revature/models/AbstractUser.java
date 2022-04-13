package com.revature.models;

import java.util.Objects;

/**
 * This AbstractUser class defines a minimum functionality for
 * interacting with users in the ERS application.
 *
 * All users in this application must at least have:
 * <ul>
 *     <li>ID</li>
 *     <li>Username</li>
 *     <li>Password</li>
 *     <li>Role</li>
 * </ul>
 *
 * Additional fields can be added to the concrete {@link com.revature.models.User} class.
 *
 * @author Center of Excellence
 */

public class AbstractUser {

    private int id;
    private String username;
    private String password;
    //adding First, Last and email to template
    private String first;
    private  String last;
    private  String email;
    private Role role;

    public AbstractUser() {
        super();
    }

//    public AbstractUser(int id, String username, String password, Role role) {
//        super();
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }

    public AbstractUser(int id, String username, String password, String first, String last, String email, Role role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractUser that = (AbstractUser) o;
        return id == that.id && Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
//    @Override
//    public String toString() {
//        return "AbstractUser{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + role +
//                '}';
//    }
}
