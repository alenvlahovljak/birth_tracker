package com.bt.servlets;

import com.bt.controllers.OrderController;
import com.bt.controllers.OrganizationController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrderController orderController = new OrderController();
            String command = request.getParameter("command");

            if (command.equals("DELETE")) {
                orderController.deleteOrderController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrderController orderController = new OrderController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            switch (command) {
                case "CREATE":
                    orderController.createOrderController(request, response);
                    break;
                case "RATING":
                    orderController.updateOrderRatingController(request, response);
                    break;
                case "DISCOUNT":
                    orderController.updateOrderDiscountController(request, response);
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
