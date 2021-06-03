package com.bt.servlets;

import com.bt.controllers.PartyController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PartyServlet", value = "/PartyServlet")
public class PartyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PartyController partyController = new PartyController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "LIST";
            }

            switch (command) {
                case "LIST":
                    partyController.getPartiesController(request, response);
                    break;
                case "ADD":
                    partyController.addPartyController(request, response);
                    break;
                case "LOAD":
                    partyController.getPartyController(request, response);
                    break;
                case "EDIT":
                    partyController.editPartyController(request, response);
                    break;
                case "DELETE":
                    partyController.deletePartyController(request, response);
                default:
                    partyController.getPartiesController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PartyController partyController = new PartyController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            switch (command) {
                case "CREATE":
                    partyController.createPartyController(request, response);
                    break;
                case "UPDATE":
                    partyController.updatePartyController(request, response);
                    break;
                default:
                    partyController.getPartiesController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
