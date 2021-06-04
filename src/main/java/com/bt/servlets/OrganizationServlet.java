package com.bt.servlets;

import com.bt.controllers.OrganizationController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/OrganizationServlet")
public class OrganizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrganizationController organizationController = new OrganizationController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }

            switch (command) {
                case "LIST":
                    organizationController.getOrganizationsController(request, response);
                    break;
                case "ADD":
                    organizationController.addOrganizationController(request,response);
                    break;
                case "LOAD":
                    organizationController.getOrganizationController(request, response);
                    break;
                case "EDIT":
                    organizationController.editOrganizationController(request,response);
                    break;
                case "DELETE":
                    organizationController.deleteOrganizationController(request, response);
                default:
                    organizationController.getOrganizationsController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrganizationController organizationController = new OrganizationController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            switch (command) {
                case "CREATE":
                    organizationController.createOrganizationController(request, response);
                    break;
                case "UPDATE":
                    organizationController.updateOrganizationController(request, response);
                    break;
                default:
                    organizationController.getOrganizationsController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
