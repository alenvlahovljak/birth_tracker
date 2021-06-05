package com.bt.controllers;

import com.bt.DAO.OrderDAO;
import com.bt.DAO.PartyDAO;
import com.bt.bean.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController {
    OrderDAO orderDAO = new OrderDAO();
    PartyDAO partyDAO = new PartyDAO();

    public void createOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int partyId = Integer.parseInt(request.getParameter("party_id"));

        Order order = new Order(userId, partyId);
        orderDAO.createOrder(order);
        partyDAO.updatePartyParticipants(partyId, "+");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + partyId);
    }

    public void updateOrderRatingController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int partyId = Integer.parseInt(request.getParameter("party_id"));
        int rating = Integer.parseInt(request.getParameter("rating"));

        Order order = new Order(id, partyId, rating);
        orderDAO.updateOrderRating(order);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + partyId);
    }

    public void updateOrderDiscountController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        int partyId = Integer.parseInt(request.getParameter("party_id"));
        boolean hasDiscount = Boolean.parseBoolean(request.getParameter("discount"));

        Order order = new Order(id, partyId, hasDiscount);
        orderDAO.updateOrderDiscount(order);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + partyId);
    }

    public void deleteOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        int partyId = Integer.parseInt(request.getParameter("party_id"));

        orderDAO.deleteOrder(id);
        partyDAO.updatePartyParticipants(partyId, "-");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + partyId);
    }
}
