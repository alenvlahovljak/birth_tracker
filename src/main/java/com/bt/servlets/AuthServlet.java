package com.bt.servlets;

import com.bt.controllers.AuthController;
import com.bt.controllers.PartyController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AuthServlet", value = "/AuthServlet")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthController authController = new AuthController();
            String role = request.getParameter("role");

            if (role == null) {
                role = "user";
            }

            switch (role) {
                case "admin":
                    authController.getAdminAuthController(request, response);
                    break;
                case "manager":
                    authController.getManagerAuthController(request, response);
                    break;
                default:
                    authController.getUserAuthController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AuthController authController = new AuthController();
            String role = request.getParameter("role");

            if (role == null) {
                role = "user";
            }

            switch (role) {
                case "admin":
                    authController.getAdminAuthController(request, response);
                    break;
                case "manager":
                    authController.getManagerAuthController(request, response);
                    break;
                default:
                    authController.setUserAuthController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
