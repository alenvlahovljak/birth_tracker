package com.bt.bean;

public class Admin {
    private int id;
    private String username;
    private String password;
    private int role;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Admin(int id, String username, String password, int role) {
        this(username, password, role);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
