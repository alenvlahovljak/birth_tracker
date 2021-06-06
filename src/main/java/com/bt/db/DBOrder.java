package com.bt.db;

import com.bt.DAO.OrderDAO;
import com.bt.bean.Order;

import javax.servlet.http.HttpServletRequest;

public class DBOrder {
    HttpServletRequest request;

    private int id;
    private int userId;
    private int partyId;
    private float rating;
    private boolean discount;

    public DBOrder(HttpServletRequest request) {
        this.request = request;
    }

    public void setParams(String id, String partyId) {
        this.id = Integer.parseInt(request.getParameter(id));
        this.partyId = Integer.parseInt(request.getParameter(partyId));
    }

    public void setParams(String id, String userId, String partyId, String rating, String discount) {
        this.setParams(id, partyId);
        this.userId = Integer.parseInt(request.getParameter(userId));
        this.rating = Float.parseFloat(request.getParameter(rating));
        this.discount = Boolean.parseBoolean(request.getParameter(discount));
    }

    public void executeSetter(String action) throws Exception {
        OrderDAO orderDAO = new OrderDAO();

        if (action.equals("delete")) {
            orderDAO.deleteOrder(this.id);
        }

        Order order = new Order(this.id, this.userId, this.partyId, this.rating, this.discount);

        if (action.equals("create")) {
            orderDAO.createOrder(order);
        }
    }

    public void executeSetter(String action, String column) throws Exception {
        OrderDAO orderDAO = new OrderDAO();

        Order order = new Order(this.id, this.userId, this.partyId, this.rating, this.discount);

        if (action.equals("update")) {
            if (column.equals("rating")) {
                orderDAO.updateOrderRating(order);
            } else if (column.equals("discount")) {
                orderDAO.updateOrderDiscount(order);
            }
        }

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPartyId() {
        return partyId;
    }

    public float getRating() {
        return rating;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPartyId(int partyId) {
        this.partyId = partyId;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }
}
