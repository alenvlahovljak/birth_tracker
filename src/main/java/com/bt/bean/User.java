package com.bt.bean;

public class User {
    private int id;
    private String role;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String avatar;

    public User(int id, String role, String username, String password, String firstName, String lastName, String avatar) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public User(int id, String role, String username, String firstName, String lastName, String avatar) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public User(String username, String firstName, String lastName, String avatar) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public User(int id, String username, String firstName, String lastName, String avatar) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
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
}
