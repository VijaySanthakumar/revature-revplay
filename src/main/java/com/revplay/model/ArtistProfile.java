package com.revplay.model;

public class ArtistProfile {

    private int artistId;
    private String bio;
    private String genre;
    private String socialLinks;

    // ===== GETTERS & SETTERS =====

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(String socialLinks) {
        this.socialLinks = socialLinks;
    }

    @Override
    public String toString() {
        return
            "Artist ID: " + artistId +
            "\nGenre: " + genre +
            "\nBio: " + bio +
            "\nSocial Links: " + socialLinks;
    }
}
