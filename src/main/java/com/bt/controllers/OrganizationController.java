package com.bt.controllers;

import com.bt.db.DBOrganization;
import com.bt.utils.AuthorizationUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrganizationController {
    AuthorizationUtil authorizationUtil;

    public void getOrganizationsController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

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

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/organizations.jsp");
        rd.forward(request, response);
    }

    public void getOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        request.setAttribute("organization", dbOrganization.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/organization.jsp");
        rd.forward(request, response);
    }

    public void addOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(1) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/add-organization.jsp");
        rd.forward(request, response);
    }

    public void createOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(1) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("name", "abbreviation", "description", "thumbnail_url", "manager_id");
        dbOrganization.executeSetter("create");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST&manager_id=" + dbOrganization.getManagerId());
    }

    public void editOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        request.setAttribute("organization", dbOrganization.executeGetter("one"));

        RequestDispatcher rd = request.getRequestDispatcher("./views/organization/edit-organization.jsp");
        rd.forward(request, response);
    }

    public void updateOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id", "name", "abbreviation", "description", "thumbnail_url", "manager_id");
        dbOrganization.executeSetter("update");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST&manager_id=" + dbOrganization.getManagerId());
    }

    public void deleteOrganizationController(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authorizationUtil = new AuthorizationUtil(request, response);

        if (authorizationUtil.hasRole(2) || authorizationUtil.hasRole(3)) {
            response.sendRedirect(request.getContextPath() + "/PartyServlet?command=LIST");
            return;
        }

        DBOrganization dbOrganization = new DBOrganization(request);

        dbOrganization.setParams("id");
        dbOrganization.executeSetter("delete");

        response.sendRedirect(request.getContextPath() + "/OrganizationServlet?command=LIST&manager_id=" + dbOrganization.getManagerId());
    }
}
