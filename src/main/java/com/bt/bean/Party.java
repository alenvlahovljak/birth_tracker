package com.bt.bean;

public class Party {
    private int id;
    private String name;
    private String description;
    private String thumbnail;
    private int participants;
    private int maxParticipants;
    private boolean hasFreeSpots;
    private int organizationId;

    public Party(String name, String description, String thumbnail, int organizationId) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.organizationId = organizationId;
    }

    public Party(String name, String description, String thumbnail, int maxParticipants, boolean hasFreeSpots, int organizationId) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.maxParticipants = maxParticipants;
        this.hasFreeSpots = hasFreeSpots;
        this.organizationId = organizationId;
    }

    public Party(int id, String name, String description, String thumbnail, int maxParticipants) {
        this(name, description, thumbnail, maxParticipants);
        this.id = id;
    }

    public Party(String name, String description, String thumbnail, int maxParticipants, int organizationId) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.maxParticipants = maxParticipants;
        this.organizationId = organizationId;
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

    public Party(int id, String name, String description, String thumbnail, int participants, int maxParticipants, boolean hasFreeSpots, int organizationId) {
        this(id, name, description, thumbnail, participants, maxParticipants, hasFreeSpots);
        this.organizationId = organizationId;
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

    public int getOrganizationId() {
        return organizationId;
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

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
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
                ", organizationId=" + organizationId +
                '}';
    }
}
