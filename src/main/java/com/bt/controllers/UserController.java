package com.bt.controllers;

import com.bt.db.DBManager;
import com.bt.db.DBUser;
import com.bt.utils.AuthorizationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {
    AuthorizationUtil authorizationUtil;

    public void getUsersController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBUser dbUser = new DBUser(request);

        request.setAttribute("users", dbUser.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/user/users.jsp");
        rd.forward(request, response);
    }

    public void getUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBManager dbManager = new DBManager(request);

        dbManager.setParams("id");
        request.setAttribute("user", dbManager.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/user.jsp");
        rd.forward(request, response);
    }

    public void addUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/user/add-user.jsp");
        rd.forward(request, response);
    }

    public void createUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBUser dbUser = new DBUser(request);

        dbUser.setParams("username", "first_name", "last_name", "avatar_url");
        dbUser.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }

    public void editUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBUser dbUser = new DBUser(request);

        dbUser.setParams("id");
        request.setAttribute("user", dbUser.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/user/edit-user.jsp");
        rd.forward(request, response);
    }

    public void updateUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBUser dbUser = new DBUser(request);

        dbUser.setParams("id", "username", "first_name", "last_name", "avatar_url");
        dbUser.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }

    public void deleteUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBUser dbUser = new DBUser(request);

        dbUser.setParams("id");
        dbUser.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }
}
