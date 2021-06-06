package com.bt.db;

import com.bt.DAO.OrganizationDAO;
import com.bt.bean.Organization;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DBOrganization {
    HttpServletRequest request;

    private int id;
    private String name;
    private String abbreviation;
    private String description;
    private String thumbnail;
    private float rating;
    private int managerId;

    public DBOrganization(HttpServletRequest request) {
        this.request = request;
    }

    public void setParams(String id) {
        this.id = Integer.parseInt(request.getParameter(id));
    }

    public void setParams(String name, String abbreviation, String description, String thumbnail, String managerId) {
        this.name = request.getParameter(name);
        this.abbreviation = request.getParameter(abbreviation);
        this.description = request.getParameter(description);
        this.thumbnail = request.getParameter(thumbnail);
        this.managerId = Integer.parseInt(request.getParameter(managerId));
    }

    public void setParams(String id, String name, String abbreviation, String description, String thumbnail, String managerId) {
        this.setParams(name, abbreviation, description, thumbnail, managerId);
        this.id = Integer.parseInt(request.getParameter(id));
    }

    public List<Organization> executeGetter() throws Exception {
        OrganizationDAO organizationDAO = new OrganizationDAO();

        return organizationDAO.getOrganizations();
    }

    public Organization executeGetter(String quantity) throws Exception {
        OrganizationDAO organizationDAO = new OrganizationDAO();

        if (quantity.equals("one")) {
            return organizationDAO.getOrganization(this.id);
        }

        return null;
    }

    public void executeSetter(String action) throws Exception {
        OrganizationDAO organizationDAO = new OrganizationDAO();

        if (action.equals("delete")) {
            organizationDAO.deleteOrganization(this.id);
        }

        Organization organization = new Organization(this.id, this.name, this.abbreviation, this.description, this.thumbnail, this.rating, this.managerId);

        if (action.equals("create")) {
            organizationDAO.createOrganization(organization);
        } else if (action.equals("update")) {
            organizationDAO.updateOrganization(organization);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public float getRating() {
        return rating;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
}
