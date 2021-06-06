package com.bt.controllers;

import com.bt.db.DBManager;
import com.bt.utils.AuthorizationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerController {
    AuthorizationUtil authorizationUtil;

    public void getManagersController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        request.setAttribute("managers", dbManager.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/manager/managers.jsp");
        rd.forward(request, response);
    }

    public void getManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        request.setAttribute("manager", dbManager.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/manager.jsp");
        rd.forward(request, response);
    }

    public void addManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/manager/add-manager.jsp");
        rd.forward(request, response);
    }

    public void createManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("username", "first_name", "last_name", "avatar_url");
        dbManager.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }

    public void editManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        request.setAttribute("manager", dbManager.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/manager/edit-manager.jsp");
        rd.forward(request, response);
    }

    public void updateManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id", "username", "first_name", "last_name", "avatar_url");
        dbManager.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }

    public void deleteManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        dbManager.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
    }
}
