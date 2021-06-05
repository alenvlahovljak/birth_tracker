package com.bt.controllers;

import com.bt.DAO.UserDAO;
import com.bt.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthController {
    UserDAO userDAO = new UserDAO();

    public void getAdminAuthController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/admin-login.jsp");
        rd.forward(request, response);
    }

    public void getManagerAuthController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/manager-login.jsp");
        rd.forward(request, response);
    }

    public void getUserAuthController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/auth/user-login.jsp");
        rd.forward(request, response);
    }

    public void setUserAuthController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUser("3");

        request.setAttribute("user", user);

        System.out.println("user " + user.getUsername());

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

}
