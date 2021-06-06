package com.bt.controllers;

import com.bt.DAO.PartyDAO;
import com.bt.db.DBOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController {
    PartyDAO partyDAO = new PartyDAO();

    public void createOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("","user_id", "party_id", "", "");
        dbOrder.executeSetter("create");

        partyDAO.updatePartyParticipants(dbOrder.getPartyId(), "+");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }

    public void updateOrderRatingController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "", "party_id", "rating", "");
        dbOrder.executeSetter("update", "rating");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }

    public void updateOrderDiscountController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "", "party_id", "", "discount");
        dbOrder.executeSetter("update", "discount");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }

    public void deleteOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "party_id");
        dbOrder.executeSetter("delete");

        partyDAO.updatePartyParticipants(dbOrder.getPartyId(), "-");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }
}
