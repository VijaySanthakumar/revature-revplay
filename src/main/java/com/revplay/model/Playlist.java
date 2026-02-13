package com.revplay.model;

public class Playlist {

    private int id;
    private int userId;
    private String name;
    private String description;
    private boolean isPublic;

    // ===== Constructors =====
    public Playlist() {}

    public Playlist(int id, int userId, String name, String description, boolean isPublic) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
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

    public boolean isPublic() {
        return isPublic;
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

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    // ===== toString (for printing) =====
    @Override
    public String toString() {
        return id + " | " + name + " | " + description + " | " + (isPublic ? "Public" : "Private");
    }
}
