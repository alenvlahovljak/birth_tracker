package com.bt.db;

import com.bt.DAO.ManagerDAO;
import com.bt.bean.Manager;
import com.bt.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DBManager {
    HttpServletRequest request;

    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;

    Helper helper = new Helper();

    public DBManager(HttpServletRequest request) {
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

    public List<Manager> executeGetter() throws Exception {
        ManagerDAO managerDAO = new ManagerDAO();

        return managerDAO.getManagers();
    }

    public Manager executeGetter(String quantity) throws Exception {
        ManagerDAO managerDAO = new ManagerDAO();

        if (quantity.equals("one")) {
            return managerDAO.getManager(this.id);
        }

        return null;
    }

    public void executeSetter(String action) throws Exception {
        ManagerDAO managerDAO = new ManagerDAO();

        if (action.equals("delete")) {
            managerDAO.deleteManager(this.id);
        }

        Manager manager = new Manager(this.id, this.username, this.firstName, this.lastName, this.avatar);

        if (action.equals("create")) {
            managerDAO.createManager(manager);
        } else if (action.equals("update")) {
            managerDAO.updateManager(manager);
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
}
