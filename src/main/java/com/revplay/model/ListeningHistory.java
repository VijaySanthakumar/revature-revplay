package com.revplay.model;

import java.sql.Timestamp;

public class ListeningHistory {

    private int id;
    private int userId;
    private int songId;
    private String songTitle;   // ✅ ADD THIS
    private Timestamp playedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    // ✅ NEW GETTER + SETTER
    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public Timestamp getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(Timestamp playedAt) {
        this.playedAt = playedAt;
    }

    @Override
    public String toString() {
        return "Song: " + songTitle +
               " | Played At: " + playedAt;
    }
}
