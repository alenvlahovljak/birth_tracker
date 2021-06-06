package com.bt.db;

import com.bt.DAO.PartyDAO;
import com.bt.bean.Party;
import com.bt.utils.Helper;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DBParty {
    HttpServletRequest request;

    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private int participants;
    private int maxParticipants;
    private boolean hasFreeSpots;
    private float price;
    private int organizationId;
    private String organizationAbbreviation;
    private int managerId;
    private int userId;
    private int orderId;
    private int rating;
    private boolean hasDiscount;

    Helper helper = new Helper();

    public DBParty(HttpServletRequest request) {
        this.request = request;
    }

    public void setParams(String id) {
        this.id = helper.getInteger(request.getParameter(id));
    }

    public void setParams(String name, String description, String thumbnail, String maxParticipants, String price, String organizationId) {
        this.name = request.getParameter(name);
        this.description = request.getParameter(description);
        this.thumbnail = request.getParameter(thumbnail);
        this.maxParticipants = helper.getInteger(request.getParameter(maxParticipants));
        this.price = helper.getFloat(request.getParameter(price));
        this.organizationId = helper.getInteger(request.getParameter(organizationId));
    }

    public void setParams(String id, String name, String description, String thumbnail, String maxParticipants, String price, String organizationId) {
        this.setParams(name, description, thumbnail, maxParticipants, price, organizationId);
        this.id = helper.getInteger(request.getParameter(id));
    }

    public List<Party> executeGetter() throws Exception {
        PartyDAO partyDAO = new PartyDAO();

        return partyDAO.getParties();
    }

    public Party executeGetter(String quantity) throws Exception {
        PartyDAO partyDAO = new PartyDAO();

        if (quantity.equals("one")) {
            return partyDAO.getParty(this.id);
        }

        return null;
    }

    public void executeSetter(String action) throws Exception {
        PartyDAO partyDAO = new PartyDAO();

        if (action.equals("delete")) {
            partyDAO.deleteParty(this.id);
        }

        Party party = new Party(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, price, organizationId, organizationAbbreviation, managerId,userId, orderId, rating, hasDiscount);

        if (action.equals("create")) {
            partyDAO.createParty(party);
        } else if (action.equals("update")) {
            partyDAO.updateParty(party);
        }
    }

    public void executeSetter(String action, String column, String value) throws Exception {
        PartyDAO partyDAO = new PartyDAO();

        if (action.equals("update")) {
            if (column.equals("participants")) {
                partyDAO.updatePartyParticipants(this.id, value);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getParticipants() {
        return participants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public boolean isHasFreeSpots() {
        return hasFreeSpots;
    }

    public float getPrice() {
        return price;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public String getOrganizationAbbreviation() {
        return organizationAbbreviation;
    }

    public int getManagerId() {
        return managerId;
    }

    public int getUserId() {
        return userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getRating() {
        return rating;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setHasFreeSpots(boolean hasFreeSpots) {
        this.hasFreeSpots = hasFreeSpots;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public void setOrganizationAbbreviation(String organizationAbbreviation) {
        this.organizationAbbreviation = organizationAbbreviation;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }
}
