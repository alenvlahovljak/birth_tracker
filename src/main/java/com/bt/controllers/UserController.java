package com.bt.controllers;

import com.bt.DAO.UserDAO;
import com.bt.bean.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserController {
    UserDAO userDAO = new UserDAO();

    public void getUsersController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User> users = userDAO.getUsers();

        request.setAttribute("users", users);

        RequestDispatcher rd = request.getRequestDispatcher("./views/users.jsp");
        rd.forward(request, response);
    }

    public void getUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        User user = userDAO.getUser(id);

        request.setAttribute("user", user);

        RequestDispatcher rd = request.getRequestDispatcher("./views/user.jsp");
        rd.forward(request, response);
    }

    public void addUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/add-user.jsp");
        rd.forward(request, response);
    }

    public void createUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String avatar = request.getParameter("avatar_url");

        User user = new User(username, firstName, lastName, avatar);
        userDAO.createUser(user);

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }

    public void editUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        User user = userDAO.getUser(id);

        request.setAttribute("user", user);

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-user.jsp");
        rd.forward(request, response);
    }

    public void updateUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String avatar = request.getParameter("avatar_url");

        User user = new User(id, username, firstName, lastName, avatar);
        userDAO.updateUser(user);

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }

    public void deleteUserController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");

        userDAO.deleteUser(id);

        response.sendRedirect(request.getContextPath() + "/UserServlet?command=LIST");
    }
}
