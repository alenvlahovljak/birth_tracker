package com.bt.utils;

import com.bt.DAO.AdminDAO;
import com.bt.DAO.ManagerDAO;
import com.bt.DAO.UserDAO;
import com.bt.bean.Admin;
import com.bt.bean.Manager;
import com.bt.bean.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationUtil {
    AdminDAO adminDAO = new AdminDAO();
    ManagerDAO managerDAO = new ManagerDAO();
    UserDAO userDAO = new UserDAO();

    public void authenticateAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        Admin admin = adminDAO.getAdminByUsername(username);

        if (admin == null) {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=admin");
        }

        assert admin != null;
        if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
            session.setAttribute("client", admin);
            response.sendRedirect(request.getContextPath() + "/ManagerServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=admin");
        }
    }

    public void authenticateManager(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        Manager manager = managerDAO.getManagerByUsername(username);

        if (manager == null) {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=manager");
        }

        assert manager != null;
        if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
            session.setAttribute("client", manager);
            response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=manager");
        }
    }

    public void authenticateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        User user = userDAO.getUserByUsername(username);

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=user");
        }

        assert user != null;
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            session.setAttribute("client", user);
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
        } else {
            response.sendRedirect(request.getContextPath() + "/AuthServlet?role=user");
        }
    }

    public void destroyClientSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

        session.setAttribute("client", null);
        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }
}
