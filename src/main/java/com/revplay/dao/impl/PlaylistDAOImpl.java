package com.revplay.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revplay.dao.PlaylistDAO;
import com.revplay.model.Playlist;
import com.revplay.util.DBConnection;

public class PlaylistDAOImpl implements PlaylistDAO {

    @Override
    public void createPlaylist(int userId, String name, String description) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO playlists (id, user_id, name, description) " +
                         "VALUES (playlist_seq.nextval, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setString(3, description);

            ps.executeUpdate();

            System.out.println("Playlist created!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Playlist> getUserPlaylists(int userId) {

        List<Playlist> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM playlists WHERE user_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Playlist p = new Playlist();

                p.setId(rs.getInt("id"));
                p.setUserId(rs.getInt("user_id"));
                p.setName(rs.getString("name"));
                p.setDescription(rs.getString("description"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    @Override
    public void deletePlaylist(int playlistId) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM playlists WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);

            ps.executeUpdate();

            System.out.println("Playlist deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
