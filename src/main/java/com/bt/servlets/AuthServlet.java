package com.bt.servlets;

import com.bt.controllers.AuthController;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            AuthController authController = new AuthController();
            String role = request.getParameter("role");

            if (role == null) {
                role = "destroy";
            }

            switch (role) {
                case "admin":
                    authController.adminController(request, response);
                    break;
                case "manager":
                    authController.managerController(request, response);
                    break;
                case "user":
                    authController.userController(request,response);
                default:
                    authController.destroyClientSessionController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            AuthController authController = new AuthController();
            String command = request.getParameter("role");

            if (command == null) {
                command = "user";
            }

            switch (command) {
                case "admin":
                    authController.authenticateAdminController(request, response);
                    break;
                case "manager":
                    authController.authenticateManagerController(request, response);
                    break;
                default:
                    authController.authenticateUserController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
