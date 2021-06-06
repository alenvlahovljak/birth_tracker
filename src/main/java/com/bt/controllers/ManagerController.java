package com.bt.controllers;

import com.bt.db.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerController {
    public void getManagersController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        request.setAttribute("managers", dbManager.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/managers.jsp");
        rd.forward(request, response);
    }

    public void getManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        request.setAttribute("manager", dbManager.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/manager.jsp");
        rd.forward(request, response);
    }

    public void addManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/add-manager.jsp");
        rd.forward(request, response);
    }

    public void createManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        dbManager.setParams("username", "first_name", "last_name", "avatar_url");
        dbManager.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }

    public void editManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        request.setAttribute("manager", dbManager.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-manager.jsp");
        rd.forward(request, response);
    }

    public void updateManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id", "username", "first_name", "last_name", "avatar_url");
        dbManager.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }

    public void deleteManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        dbManager.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }
}
