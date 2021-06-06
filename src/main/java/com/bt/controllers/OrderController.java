package com.bt.controllers;

import com.bt.bean.User;
import com.bt.db.DBOrder;
import com.bt.db.DBParty;
import com.bt.utils.AuthorizationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderController {
    AuthorizationUtil authorizationUtil;


    public void createOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        DBParty dbParty = new DBParty(request);

        if (!authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "user_id", "party_id", "rating", "discount");
        dbOrder.executeSetter("create");

        dbParty.setParams("party_id");
        dbParty.executeSetter("update", "participants", "+");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbParty.getId());
    }

    public void updateOrderRatingController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (!authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "user_id", "party_id", "rating", "discount");
        dbOrder.executeSetter("update", "rating");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }

    public void updateOrderDiscountController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (!authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrder dbOrder = new DBOrder(request);

        dbOrder.setParams("id", "user_id", "party_id", "rating", "discount");
        dbOrder.executeSetter("update", "discount");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbOrder.getPartyId());
    }

    public void deleteOrderController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (!authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrder dbOrder = new DBOrder(request);
        DBParty dbParty = new DBParty(request);

        dbOrder.setParams("id", "party_id");
        dbOrder.executeSetter("delete");

        dbParty.setParams("party_id");
        dbParty.executeSetter("update", "participants", "-");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LOAD&id=" + dbParty.getId());
    }
}
