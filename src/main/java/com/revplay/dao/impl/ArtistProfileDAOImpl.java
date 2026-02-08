package com.revplay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.dao.ArtistProfileDAO;
import com.revplay.model.ArtistProfile;
import com.revplay.util.DBConnection;

public class ArtistProfileDAOImpl implements ArtistProfileDAO {

    private static final String INSERT_PROFILE =
        "INSERT INTO artist_profile (artist_id, bio, genre, social_links) " +
        "VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_ARTIST =
        "SELECT artist_id, bio, genre, social_links " +
        "FROM artist_profile WHERE artist_id = ?";

    private static final String UPDATE_PROFILE =
        "UPDATE artist_profile SET bio = ?, genre = ?, social_links = ? " +
        "WHERE artist_id = ?";

    // ===== CREATE PROFILE =====
    @Override
    public void createProfile(ArtistProfile profile) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_PROFILE)) {

            ps.setInt(1, profile.getArtistId());
            ps.setString(2, profile.getBio());
            ps.setString(3, profile.getGenre());
            ps.setString(4, profile.getSocialLinks());

            ps.executeUpdate();
        }
    }

    // ===== GET PROFILE =====
    @Override
    public ArtistProfile getProfileByArtistId(int artistId) throws Exception {

        ArtistProfile profile = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ARTIST)) {

            ps.setInt(1, artistId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    profile = new ArtistProfile();
                    profile.setArtistId(rs.getInt("artist_id"));
                    profile.setBio(rs.getString("bio"));
                    profile.setGenre(rs.getString("genre"));
                    profile.setSocialLinks(rs.getString("social_links"));
                }
            }
        }

        return profile;
    }

    // ===== UPDATE PROFILE =====
    @Override
    public void updateProfile(ArtistProfile profile) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_PROFILE)) {

            ps.setString(1, profile.getBio());
            ps.setString(2, profile.getGenre());
            ps.setString(3, profile.getSocialLinks());
            ps.setInt(4, profile.getArtistId());

            ps.executeUpdate();
        }
    }
}
