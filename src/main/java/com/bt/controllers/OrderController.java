package com.bt.controllers;

import com.bt.db.DBOrder;
import com.bt.db.DBParty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController {
    public void createOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrder dbOrder = new DBOrder(request);
        DBParty dbParty = new DBParty(request);

        dbOrder.setParams("", "user_id", "party_id", "", "");
        dbOrder.executeSetter("create");

        dbParty.setParams("party_id");
        dbParty.executeSetter("update", "participants", "+");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbParty.getId());
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
        DBParty dbParty = new DBParty(request);

        dbOrder.setParams("id", "party_id");
        dbOrder.executeSetter("delete");

        dbParty.setParams("party_id");
        dbParty.executeSetter("update", "participants", "-");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbParty.getId());
    }
}
