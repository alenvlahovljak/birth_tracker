package com.bt.bean;

public class Party {
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
    private int userId;
    private int orderId;
    private int rating;
    private boolean hasDiscount;

    public Party(String name, String description, String thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Party(String name, String description, String thumbnail, int maxParticipants) {
        this(name, description, thumbnail);
        this.maxParticipants = maxParticipants;
    }

    public Party(int id, String name, String description, String thumbnail, int maxParticipants) {
        this(name,description,thumbnail,maxParticipants);
        this.id = id;
    }

    public Party(String name, String description, String thumbnail, int maxParticipants, int organizationId) {
        this(name,description,thumbnail,maxParticipants);
        this.organizationId = organizationId;
    }


    public Party(String name, String description, String thumbnail, int maxParticipants, boolean hasFreeSpots, int organizationId) {
        this(name,description,thumbnail,maxParticipants);
        this.hasFreeSpots = hasFreeSpots;
        this.organizationId = organizationId;
    }

    public Party(String name, String description, String thumbnail, int maxParticipants, float price, int organizationId) {
        this(name, description, thumbnail, maxParticipants, organizationId);
        this.price = price;
    }

    public Party(int id, String name, String description, String thumbnail, int maxParticipants, int organizationId) {
        this(name, description, thumbnail, maxParticipants, organizationId);
        this.id = id;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots) {
        this(id, name, description, thumbnail, maxParticipants);
        this.participants = participants;
        this.hasFreeSpots = hasFreeSpots;
    }

    public Party(int id, String name, String description, String thumbnail, int maxParticipants, float price, int organizationId) {
        this(id, name, description, thumbnail, maxParticipants, organizationId);
        this.price = price;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, int organizationId) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots);
        this.organizationId = organizationId;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, int organizationId, String organizationAbbreviation) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots,organizationId);
        this.organizationAbbreviation = organizationAbbreviation;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, float price, int organizationId, String organizationAbbreviation) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, organizationId, organizationAbbreviation);
        this.price = price;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, int organizationId, String organizationAbbreviation, int userId, int orderId, int rating) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, organizationId, organizationAbbreviation);
        this.userId = userId;
        this.orderId = orderId;
        this.rating = rating;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, float price, int organizationId, String organizationAbbreviation, int userId, int orderId, int rating) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, organizationId, organizationAbbreviation, userId, orderId, rating);
        this.price = price;
    }

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, float price, int organizationId, String organizationAbbreviation, int userId, int orderId, int rating, boolean hasDiscount) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots, price, organizationId, organizationAbbreviation, userId, orderId, rating);
        this.hasDiscount = hasDiscount;
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

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", participants=" + participants +
                ", maxParticipants=" + maxParticipants +
                ", hasFreeSpots=" + hasFreeSpots +
                ", price=" + price +
                ", organizationId=" + organizationId +
                ", organizationAbbreviation='" + organizationAbbreviation + '\'' +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", rating=" + rating +
                ", hasDiscount=" + hasDiscount +
                '}';
    }
}
