package com.bt.controllers;

import com.bt.DAO.*;
import com.bt.bean.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PartyController {
    PartyDAO partyDAO = new PartyDAO();
    OrganizationDAO organizationDAO = new OrganizationDAO();
    OrderDAO orderDAO = new OrderDAO();

    public void getPartiesController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Party> parties = partyDAO.getParties();

        request.setAttribute("parties", parties);

        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    public void getPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Party party = partyDAO.getParty(id);
        float discount = orderDAO.getUserDiscount("3");

        request.setAttribute("party", party);
        request.setAttribute("discount", discount);

        RequestDispatcher rd = request.getRequestDispatcher("./views/party.jsp");
        rd.forward(request, response);
    }

    public void addPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Organization> organizations = organizationDAO.getOrganizations();

        request.setAttribute("organizations", organizations);

        RequestDispatcher rd = request.getRequestDispatcher("./views/add-party.jsp");
        rd.forward(request, response);
    }

    public void createPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String thumbnail = request.getParameter("thumbnail_url");
        int maxParticipants = Integer.parseInt(request.getParameter("max_participants"));
        float price = Float.parseFloat(request.getParameter("price"));
        int organizationId = Integer.parseInt(request.getParameter("organization_id"));

        Party party = new Party(name, description, thumbnail, maxParticipants, price, organizationId);
        partyDAO.createParty(party);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void editPartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Party party = partyDAO.getParty(id);
        List<Organization> organizations = organizationDAO.getOrganizations();

        request.setAttribute("party", party);
        request.setAttribute("organizations", organizations);

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-party.jsp");
        rd.forward(request, response);
    }

    public void updatePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String thumbnail = request.getParameter("thumbnail_url");
        int maxParticipants = Integer.parseInt(request.getParameter("max_participants"));
        float price = Float.parseFloat(request.getParameter("price"));
        int organizationId = Integer.parseInt(request.getParameter("organization_id"));

        Party party = new Party(id, name, description, thumbnail, maxParticipants, price, organizationId);
        partyDAO.updateParty(party);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }

    public void deletePartyController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");

        partyDAO.deleteParty(id);

        response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
    }
}