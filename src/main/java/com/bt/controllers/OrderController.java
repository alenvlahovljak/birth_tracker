package com.bt.controllers;

import com.bt.DAO.ManagerDAO;
import com.bt.DAO.OrderDAO;
import com.bt.bean.Manager;
import com.bt.bean.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderController {
    OrderDAO orderDAO = new OrderDAO();

    public void createOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int partyId = Integer.parseInt(request.getParameter("party_id"));

        Order order = new Order(userId, partyId);
        orderDAO.createOrder(order);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void updateOrderRatingController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        float rating = Float.parseFloat(request.getParameter("rating"));

        Order order = new Order(id, rating);
        orderDAO.updateOrder(order);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST&id=" + id);
    }
}
