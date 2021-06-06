package com.bt.db;

import com.bt.DAO.UserDAO;
import com.bt.bean.User;
import com.bt.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DBUser {
    HttpServletRequest request;

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;

    Helper helper = new Helper();

    public DBUser(HttpServletRequest request) {
        this.request = request;
    }

    public void setParams(String id) {
        this.id = helper.getInteger(request.getParameter(id));
    }

    public void setParams(String username, String firstName, String lastName, String avatar) {
        this.username = request.getParameter(username);
        this.firstName = request.getParameter(firstName);
        this.lastName = request.getParameter(lastName);
        this.avatar = request.getParameter(avatar);
    }

    public void setParams(String id, String username, String firstName, String lastName, String avatar) {
        this.setParams(username, firstName, lastName, avatar);
        this.id = helper.getInteger(request.getParameter(id));
    }

    public List<User> executeGetter() throws Exception {
        UserDAO userDAO = new UserDAO();

        return userDAO.getUsers();
    }

    public User executeGetter(String quantity) throws Exception {
        UserDAO userDAO = new UserDAO();

        if (quantity.equals("one")) {
            return userDAO.getUser(this.id);
        }

        return null;
    }

    public void executeSetter(String action) throws Exception {
        UserDAO userDAO = new UserDAO();

        if (action.equals("delete")) {
            userDAO.deleteUser(this.id);
        }

        User user = new User(this.id, this.username, this.firstName, this.lastName, this.avatar);

        if (action.equals("create")) {
            userDAO.createUser(user);
        } else if (action.equals("update")) {
            userDAO.updateUser(user);
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
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
