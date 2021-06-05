package com.bt.controllers;

import com.bt.DAO.AdminDAO;
import com.bt.DAO.ManagerDAO;
import com.bt.DAO.UserDAO;
import com.bt.bean.Admin;
import com.bt.bean.Manager;
import com.bt.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthController {
    AdminDAO adminDAO = new AdminDAO();
    ManagerDAO managerDAO = new ManagerDAO();
    UserDAO userDAO = new UserDAO();

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
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Admin admin = adminDAO.getAdminByUsername(username);

        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            session.setAttribute("client", admin);
            response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=admin");
        }
    }

    public void authenticateManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Manager manager = managerDAO.getManagerByUsername(username);

        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            session.setAttribute("client", manager);
            response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=manager");
        }
    }

    public void authenticateUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUserByUsername(username);

        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("client", user);
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=user");
        }
    }
}
