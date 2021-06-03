package com.bt.controllers;

import com.bt.DAO.ManagerDAO;
import com.bt.bean.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerController {
    ManagerDAO managerDAO = new ManagerDAO();

    public void getManagersController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Manager> managers = managerDAO.getManagers();

        request.setAttribute("managers", managers);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    public void getManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Manager manager = managerDAO.getManager(id);

        request.setAttribute("manager", manager);

        RequestDispatcher rd = request.getRequestDispatcher("./views/party.jsp");
        rd.forward(request, response);
    }

    public void createManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String avatar = request.getParameter("avatar_url");

        Manager manager = new Manager(name, username, firstName, lastName, avatar);
        managerDAO.createManager(manager);

        response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
    }

    public void updateManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String avatar = request.getParameter("avatar_url");

        Manager manager = new Manager(id, name, username, firstName, lastName, avatar);
        managerDAO.updateManager(manager);

        response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
    }

    public void deleteManagerController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");

        managerDAO.deleteManager(id);

        response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
    }
}