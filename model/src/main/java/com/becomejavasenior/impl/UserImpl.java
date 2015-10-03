package com.becomejavasenior.impl;

import com.becomejavasenior.Identity;
import com.becomejavasenior.Role;
import com.becomejavasenior.User;

import java.util.Date;

/**
 * JavaBean class describes UserImpl model
 *
 * @author  Andrey Radionov <andyomsk@gmail.com>
 * @version 0.1
 */

public class UserImpl extends IdentityImpl implements Identity, User {

    private static final long serialVersionUID = 1L;

    private Role role;

    private String userName;

    private String password;

    private String lastName;

    private String firstName;

    private String email;

    private Date created;

    private Date updated;

    public UserImpl() {
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public Role getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "id=" + super.getId() +
                ", role=" + getRole() +
                ", userName='" + userName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", password=" + password +
                '}';
    }
}
