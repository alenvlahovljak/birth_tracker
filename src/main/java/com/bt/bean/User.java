package com.bt.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String role;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;

    public User(String username, String firstName, String lastName, String avatar) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public User(int id, String username, String firstName, String lastName, String avatar) {
        this(username, firstName, lastName, avatar);
        this.id = id;
    }

    public User(int id, String role, String username, String firstName, String lastName, String avatar) {
        this(id, username, firstName, lastName, avatar);
        this.role = role;
    }

    public User(int id, String role, String username, String password, String firstName, String lastName, String avatar) {
        this(id, role, username, firstName, lastName, avatar);
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
