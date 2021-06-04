package com.bt.servlets;

import com.bt.controllers.ManagerController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManagerServlet", value = "/ManagerServlet")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ManagerController managerController = new ManagerController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }

            switch (command) {
                case "LIST":
                    managerController.getManagersController(request, response);
                    break;
                case "ADD":
                    managerController.addManagerController(request, response);
                    break;
                case "LOAD":
                    managerController.getManagerController(request, response);
                    break;
                case "EDIT":
                    managerController.editManagerController(request, response);
                    break;
                case "DELETE":
                    managerController.deleteManagerController(request, response);
                default:
                    managerController.getManagersController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ManagerController managerController = new ManagerController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            switch (command) {
                case "CREATE":
                    managerController.createManagerController(request, response);
                    break;
                case "UPDATE":
                    managerController.updateManagerController(request, response);
                    break;
                default:
                    managerController.getManagersController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
