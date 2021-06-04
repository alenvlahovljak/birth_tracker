package com.bt.servlets;

import com.bt.controllers.OrderController;
import com.bt.controllers.UserController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrderController orderController = new OrderController();
            String command = request.getParameter("command");

            if (command == null) {
                command = "CREATE";
            }

            if (command.equals("CREATE")) {
                orderController.createOrderController(request, response);
            } else {
                orderController.updateOrderRatingController(request, response);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
