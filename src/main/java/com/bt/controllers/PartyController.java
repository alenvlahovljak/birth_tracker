package com.bt.controllers;

import com.bt.bean.Manager;
import com.bt.bean.User;
import com.bt.db.DBOrder;
import com.bt.db.DBOrganization;
import com.bt.db.DBParty;
import com.bt.utils.AuthorizationUtil;
import com.bt.utils.Helper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PartyController {
    AuthorizationUtil authorizationUtil;
    Helper helper = new Helper();

    public void getPartiesController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBParty dbParty = new DBParty(request);

        request.setAttribute("parties", dbParty.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    public void getPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

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

        RequestDispatcher rd = request.getRequestDispatcher("./views/party/party.jsp");
        rd.forward(request, response);
    }

    public void addPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.getClientSession() == null) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        if (authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrganization dbOrganization = new DBOrganization(request);
        dbOrganization.setParams("", "", "", "", "manager_id");

        if (authorizationUtil.hasRole(1)) {
            request.setAttribute("organizations", dbOrganization.executeGetter());
        }
        if (authorizationUtil.hasRole(2)) {
            request.setAttribute("organizations", dbOrganization.executeGetter("one", "manager_id"));
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/party/add-party.jsp");
        rd.forward(request, response);
    }

    public void createPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.getClientSession() == null) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        if (authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBParty dbParty = new DBParty(request);

        dbParty.setParams("name", "description", "thumbnail_url", "max_participants", "price", "organization_id");
        dbParty.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void editPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        Manager manager;

        try {
            manager = (Manager) authorizationUtil.getClientSession();
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        if (!authorizationUtil.hasRole(1) && manager.getId() != helper.getInteger(request.getParameter("manager_id"))) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBParty dbParty = new DBParty(request);
        DBOrganization dbOrganization = new DBOrganization(request);

        dbParty.setParams("id");
        dbOrganization.setParams("id", "", "", "", "manager_id");
        request.setAttribute("party", dbParty.executeGetter("one"));
        request.setAttribute("organizations", dbOrganization.executeGetter());

        if (authorizationUtil.hasRole(1)) {
            request.setAttribute("organizations", dbOrganization.executeGetter());
        }
        if (authorizationUtil.hasRole(2)) {
            request.setAttribute("organizations", dbOrganization.executeGetter("one", "manager_id"));
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/party/edit-party.jsp");
        rd.forward(request, response);
    }

    public void updatePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        Manager manager;

        try {
            manager = (Manager) authorizationUtil.getClientSession();
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        if (!authorizationUtil.hasRole(1) && manager.getId() != helper.getInteger(request.getParameter("manager_id"))) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBParty dbParty = new DBParty(request);

        dbParty.setParams("id", "name", "description", "thumbnail_url", "max_participants", "price", "organization_id");
        dbParty.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void deletePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        Manager manager;

        try {
            manager = (Manager) authorizationUtil.getClientSession();
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        if (!authorizationUtil.hasRole(1) && manager.getId() != helper.getInteger(request.getParameter("manager_id"))) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBParty dbParty = new DBParty(request);

        dbParty.setParams("id");
        dbParty.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }
}