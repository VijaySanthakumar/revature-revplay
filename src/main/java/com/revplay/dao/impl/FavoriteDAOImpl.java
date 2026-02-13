package com.revplay.dao.impl;

import com.revplay.dao.FavoriteDAO;
import com.revplay.model.Favorite;
import com.revplay.model.Song;
import com.revplay.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the FavoriteDAO interface.
 * 
 * @author Vijay
 */
public class FavoriteDAOImpl implements FavoriteDAO {

    // ===== ADD FAVORITE =====
    @Override
    public void addFavorite(int userId, int songId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO favorites (id, user_id, song_id) VALUES (favorites_seq.nextval, ?, ?)";


        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);
        ps.setInt(2, songId);

        ps.executeUpdate();

        System.out.println("Added to favorites!");
    }


    // ===== REMOVE FAVORITE =====
    @Override
    public void removeFavorite(int userId, int songId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM favorites WHERE user_id=? AND song_id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, userId);
        ps.setInt(2, songId);

        ps.executeUpdate();

        System.out.println("Removed from favorites!");
    }


    // ===== GET USER FAVORITES =====
    @Override
    public List<Favorite> getUserFavorites(int userId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM favorites WHERE user_id=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        List<Favorite> list = new ArrayList<>();

        while (rs.next()) {

            Favorite f = new Favorite();

            f.setId(rs.getInt("id"));
            f.setUserId(rs.getInt("user_id"));
            f.setSongId(rs.getInt("song_id"));

            list.add(f);
        }

        return list;
    }
    
    @Override
    public List<Song> viewFavorites(int userId) throws Exception {

        List<Song> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT s.id, s.title, s.genre, s.duration, s.play_count " +
            "FROM favorites f JOIN songs s ON f.song_id = s.id " +
            "WHERE f.user_id = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, userId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Song song = new Song();

            song.setId(rs.getInt("id"));
            song.setTitle(rs.getString("title"));
            song.setGenre(rs.getString("genre"));
            song.setDuration(rs.getInt("duration"));
            song.setPlayCount(rs.getInt("play_count"));

            list.add(song);
        }

        return list;
    }

    @Override
    public Map<String, List<String>> getUsersWhoFavoritedArtistSongs(int artistId) throws Exception {

        Map<String, List<String>> result = new HashMap<>();

        Connection con = DBConnection.getConnection();

        String sql =
            "SELECT s.title as song_title, u.username, u.email " +
            "FROM favorites f " +
            "JOIN songs s ON f.song_id = s.id " +
            "JOIN users u ON f.user_id = u.id " +
            "WHERE s.artist_id = ? " +
            "ORDER BY s.title, u.username";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, artistId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String songTitle = rs.getString("song_title");
            String userInfo = rs.getString("username") + " (" + rs.getString("email") + ")";

            result.computeIfAbsent(songTitle, k -> new ArrayList<>()).add(userInfo);
        }

        return result;
    }

}
