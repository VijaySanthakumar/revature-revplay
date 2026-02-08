package com.revplay.model;

public class Song {

    private int id;
    private String title;
    private String genre;
    private int duration;
    private int playCount;

    // 🔹 Artist who uploaded the song
    private int artistId;

    // 🔹 Album this song belongs to (nullable)
    private Integer albumId;

    // ===== GETTERS & SETTERS =====

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    // ===== ARTIST ID =====
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    // ===== ALBUM ID =====
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + genre +
               " | " + duration + " sec | Plays: " + playCount;
    }
}
