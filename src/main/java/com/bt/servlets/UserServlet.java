package com.bt.servlets;

import com.bt.controllers.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserController userController = new UserController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }

            switch (command) {
                case "LIST":
                    userController.getUsersController(request, response);
                    break;
                case "ADD":
                    userController.addUserController(request, response);
                    break;
                case "LOAD":
                    userController.getUserController(request, response);
                    break;
                case "EDIT":
                    userController.editUserController(request, response);
                    break;
                case "DELETE":
                    userController.deleteUserController(request, response);
                default:
                    userController.getUsersController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserController userController = new UserController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            switch (command) {
                case "CREATE":
                    userController.createUserController(request, response);
                    break;
                case "UPDATE":
                    userController.updateUserController(request, response);
                    break;
                default:
                    userController.getUsersController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
