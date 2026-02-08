package com.revplay.model;

public class Playlist {

    private int id;
    private int userId;
    private String name;
    private String description;

    // ===== Constructors =====
    public Playlist() {}

    public Playlist(int id, int userId, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    // ===== Getters =====
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // ===== Setters =====
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // ===== toString (for printing) =====
    @Override
    public String toString() {
        return id + " | " + name + " | " + description;
    }
}
