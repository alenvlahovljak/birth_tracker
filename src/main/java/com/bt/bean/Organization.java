package com.bt.bean;

public class Organization {
    private int id;
    private String name;
    private String abbreviation;
    private String description;
    private String thumbnail;
    private float rating;
    private int managerId;

    public Organization(String name, String abbreviation, String description, String thumbnail, int managerId) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
        this.thumbnail = thumbnail;
        this.managerId = managerId;
    }

    public Organization(String name, String abbreviation, String description, String thumbnail) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail, int managerId) {
        this(name, abbreviation, description, thumbnail, managerId);
        this.id = id;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail, float rating, int managerId) {
        this(id, name, abbreviation, description, thumbnail, managerId);
        this.rating = rating;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
        this.thumbnail = thumbnail;
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
