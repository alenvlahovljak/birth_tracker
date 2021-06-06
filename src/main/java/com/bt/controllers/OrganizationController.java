package com.bt.controllers;

import com.bt.db.DBOrganization;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrganizationController {
    public void getOrganizationsController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        request.setAttribute("organizations", dbOrganization.executeGetter());

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/organizations.jsp");
        rd.forward(request, response);
    }

    public void getOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        request.setAttribute("organization", dbOrganization.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization.jsp");
        rd.forward(request, response);
    }

    public void addOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher("./views/add-organization.jsp");
        rd.forward(request, response);
    }

    public void createOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("name", "abbreviation", "description", "thumbnail_url", "manager_id");
        dbOrganization.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }

    public void editOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        request.setAttribute("organization", dbOrganization.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/edit-organization.jsp");
        rd.forward(request, response);
    }

    public void updateOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id", "name", "abbreviation", "description", "thumbnail_url", "manager_id");
        dbOrganization.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }

    public void deleteOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        dbOrganization.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST");
    }
}
