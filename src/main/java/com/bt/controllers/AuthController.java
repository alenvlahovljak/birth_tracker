package com.bt.controllers;

import com.bt.DAO.AdminDAO;
import com.bt.DAO.ManagerDAO;
import com.bt.DAO.UserDAO;
import com.bt.bean.Admin;
import com.bt.bean.Manager;
import com.bt.bean.User;
import com.bt.utils.AuthenticationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthController {
    AdminDAO adminDAO = new AdminDAO();
    ManagerDAO managerDAO = new ManagerDAO();
    UserDAO userDAO = new UserDAO();

    AuthenticationUtil authenticationUtil = new AuthenticationUtil();

    public void adminController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/admin-login.jsp");
        rd.forward(request, response);
    }

    public void managerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/manager-login.jsp");
        rd.forward(request, response);
    }

    public void userController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/user-login.jsp");
        rd.forward(request, response);
    }

    public void authenticateAdminController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticationUtil.authenticateAdmin(request, response);
    }

    public void authenticateManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticationUtil.authenticateManager(request, response);
    }

    public void authenticateUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authenticationUtil.authenticateUser(request, response);
    }
}
