package com.bt.controllers;

import com.bt.bean.User;
import com.bt.db.DBOrder;
import com.bt.db.DBOrganization;
import com.bt.db.DBParty;
import com.bt.utils.AuthorizationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PartyController {
    public void getPartiesController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);

        request.setAttribute("parties", dbParty.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    public void getPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuthorizationUtil authorizationUtil = new AuthorizationUtil(request, response);

        Object client;
        User user;

        client = authorizationUtil.getClientSession();

        if (client == null) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBParty dbParty = new DBParty(request);
        dbParty.setParams("id");
        request.setAttribute("party", dbParty.executeGetter("one"));

        if (authorizationUtil.hasRole(3)) {
            user = (User) client;

            DBOrder dbOrder = new DBOrder(request);
            dbOrder.setUserId(user.getId());

            request.setAttribute("discount", dbOrder.executeGetter("one", "discount"));
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/party.jsp");
        rd.forward(request, response);
    }

    public void addPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        request.setAttribute("organizations", dbOrganization.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/add-party.jsp");
        rd.forward(request, response);
    }

    public void createPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);

        dbParty.setParams("name", "description", "thumbnail_url", "max_participants", "price", "organization_id");
        dbParty.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void editPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);
        DBOrganization dbOrganization = new DBOrganization(request);

        dbParty.setParams("id");
        request.setAttribute("party", dbParty.executeGetter("one"));
        request.setAttribute("organizations", dbOrganization.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-party.jsp");
        rd.forward(request, response);
    }

    public void updatePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);

        dbParty.setParams("id", "name", "description", "thumbnail_url", "max_participants", "price", "organization_id");
        dbParty.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void deletePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);

        dbParty.setParams("id");
        dbParty.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }
}