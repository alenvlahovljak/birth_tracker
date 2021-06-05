package com.bt.controllers;

import com.bt.DAO.OrganizationDAO;
import com.bt.bean.Organization;
import com.bt.bean.Party;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrganizationController {
    OrganizationDAO organizationDAO = new OrganizationDAO();

    public void getOrganizationsController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Organization> organizations = organizationDAO.getOrganizations();

        request.setAttribute("organizations", organizations);

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/organizations.jsp");
        rd.forward(request, response);
    }

    public void getOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Organization organization = organizationDAO.getOrganization(id);

        request.setAttribute("organization", organization);

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization.jsp");
        rd.forward(request, response);
    }

    public void addOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/add-organization.jsp");
        rd.forward(request, response);
    }

    public void createOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String abbreviation = request.getParameter("abbreviation");
        String description = request.getParameter("description");
        String thumbnail = request.getParameter("thumbnail_url");
        int managerId = Integer.parseInt(request.getParameter("manager_id"));

        Organization organization = new Organization(name, abbreviation, description, thumbnail, managerId);
        organizationDAO.createOrganization(organization);

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }

    public void editOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Organization organization = organizationDAO.getOrganization(id);

        request.setAttribute("organization", organization);

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-organization.jsp");
        rd.forward(request, response);
    }

    public void updateOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String abbreviation = request.getParameter("abbreviation");
        String description = request.getParameter("description");
        String thumbnail = request.getParameter("thumbnail_url");

        Organization organization = new Organization(id, name, abbreviation, description, thumbnail);
        organizationDAO.updateOrganization(organization);

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }

    public void deleteOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");

        organizationDAO.deleteOrganization(id);

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }
}
