package com.revplay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revplay.dao.AlbumDAO;
import com.revplay.model.Album;
import com.revplay.util.DBConnection;

public class AlbumDAOImpl implements AlbumDAO {

    private static final String INSERT_ALBUM =
        "INSERT INTO albums (id, artist_id, title, release_date) " +
        "VALUES (albums_seq.NEXTVAL, ?, ?, ?)";

    private static final String SELECT_BY_ARTIST =
        "SELECT id, artist_id, title, release_date " +
        "FROM albums WHERE artist_id = ?";

    private static final String SELECT_BY_ID =
        "SELECT id, artist_id, title, release_date " +
        "FROM albums WHERE id = ?";

    private static final String DELETE_ALBUM =
        "DELETE FROM albums WHERE id = ? AND artist_id = ?";

    // ================= CREATE ALBUM =================
    @Override
    public void createAlbum(Album album) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_ALBUM)) {

            ps.setInt(1, album.getArtistId());
            ps.setString(2, album.getTitle());
            ps.setDate(3, album.getReleaseDate());

            ps.executeUpdate();
        }
    }

    // ================= VIEW ALBUMS BY ARTIST =================
    @Override
    public List<Album> getAlbumsByArtist(int artistId) throws Exception {

        List<Album> albums = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ARTIST)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Album album = new Album();
                album.setId(rs.getInt("id"));
                album.setArtistId(rs.getInt("artist_id"));
                album.setTitle(rs.getString("title"));
                album.setReleaseDate(rs.getDate("release_date"));
                albums.add(album);
            }
        }

        return albums;
    }

    // ================= GET ALBUM BY ID =================
    @Override
    public Album getAlbumById(int albumId) throws Exception {

        Album album = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {

            ps.setInt(1, albumId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                album = new Album();
                album.setId(rs.getInt("id"));
                album.setArtistId(rs.getInt("artist_id"));
                album.setTitle(rs.getString("title"));
                album.setReleaseDate(rs.getDate("release_date"));
            }
        }

        return album;
    }

    // ================= DELETE ALBUM =================
    @Override
    public void deleteAlbum(int albumId, int artistId) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_ALBUM)) {

            ps.setInt(1, albumId);
            ps.setInt(2, artistId);
            ps.executeUpdate();
        }
    }
}
