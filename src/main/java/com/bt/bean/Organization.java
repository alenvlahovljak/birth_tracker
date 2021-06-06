package com.bt.bean;

import java.io.Serializable;

public class Organization implements Serializable {
    private int id;
    private String name;
    private String abbreviation;
    private String description;
    private String thumbnail;
    private float rating;
    private int managerId;


    public Organization(String name, String abbreviation, String description, String thumbnail) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Organization(String name, String abbreviation, String description, String thumbnail, int managerId) {
        this(name, abbreviation, description, thumbnail);
        this.managerId = managerId;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail) {
        this(name, abbreviation, description, thumbnail);
        this.id = id;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail, int managerId) {
        this(id, name, abbreviation, description, thumbnail);
        this.managerId = managerId;
    }

    public Organization(int id, String name, String abbreviation, String description, String thumbnail, float rating, int managerId) {
        this(id, name, abbreviation, description, thumbnail, managerId);
        this.rating = rating;
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

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", rating=" + rating +
                ", managerId=" + managerId +
                '}';
    }
}
