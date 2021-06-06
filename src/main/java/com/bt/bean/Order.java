package com.bt.bean;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int userId;
    private int partyId;
    private float rating;
    private boolean hasDiscount;

    public Order(int userId, int partyId) {
        this.userId = userId;
        this.partyId = partyId;
    }

    public Order(int id, int partyId, float rating) {
        this.id = id;
        this.partyId = partyId;
        this.rating = rating;
    }

    public Order(int id, int partyId, boolean hasDiscount) {
        this.id = id;
        this.partyId = partyId;
        this.hasDiscount = hasDiscount;
    }

    public Order(int id, int userId, int partyId, float rating, boolean hasDiscount) {
        this(id, partyId, rating);
        this.userId = userId;
        this.hasDiscount = hasDiscount;
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

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
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

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", partyId=" + partyId +
                ", rating=" + rating +
                ", hasDiscount=" + hasDiscount +
                '}';
    }
}
