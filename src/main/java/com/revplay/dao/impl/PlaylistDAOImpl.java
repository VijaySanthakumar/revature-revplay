package com.revplay.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revplay.dao.PlaylistDAO;
import com.revplay.model.Playlist;
import com.revplay.model.Song;
import com.revplay.util.DBConnection;

public class PlaylistDAOImpl implements PlaylistDAO {

    @Override
    public void createPlaylist(int userId, String name, String description, boolean isPublic) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO playlists (id, user_id, name, description, ispublic) " +
                         "VALUES (playlist_seq.nextval, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setInt(4, isPublic ? 1 : 0);

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

                p.setPublic(rs.getInt("ispublic") == 1);

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

            // First delete all songs from playlist_songs
            String deleteSongsSql = "DELETE FROM playlist_songs WHERE playlist_id = ?";
            PreparedStatement deleteSongsPs = con.prepareStatement(deleteSongsSql);
            deleteSongsPs.setInt(1, playlistId);
            deleteSongsPs.executeUpdate();

            // Then delete the playlist
            String sql = "DELETE FROM playlists WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);

            ps.executeUpdate();

            System.out.println("Playlist deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePlaylist(int playlistId, String name, String description, boolean isPublic) throws Exception {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE playlists SET name = ?, description = ?, ispublic = ? WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, isPublic ? 1 : 0);
            ps.setInt(4, playlistId);

            ps.executeUpdate();

            System.out.println("Playlist updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSongToPlaylist(int playlistId, int songId) throws Exception {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO playlist_songs (id, playlist_id, song_id) VALUES (playlist_songs_seq.nextval, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            ps.executeUpdate();

            System.out.println("Song added to playlist!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSongFromPlaylist(int playlistId, int songId) throws Exception {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM playlist_songs WHERE playlist_id = ? AND song_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            ps.executeUpdate();

            System.out.println("Song removed from playlist!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Song> getSongsInPlaylist(int playlistId) throws Exception {

        List<Song> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT s.id, s.title, s.genre, s.duration, s.play_count, s.artist_id " +
                         "FROM songs s JOIN playlist_songs ps ON s.id = ps.song_id " +
                         "WHERE ps.playlist_id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                s.setArtistId(rs.getInt("artist_id"));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Playlist> getPublicPlaylists() throws Exception {

        List<Playlist> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT p.*, u.username as creator_name FROM playlists p " +
                         "JOIN users u ON p.user_id = u.id " +
                         "WHERE p.ispublic = 1";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Playlist p = new Playlist();
                p.setId(rs.getInt("id"));
                p.setUserId(rs.getInt("user_id"));
                p.setName(rs.getString("name") + " (by " + rs.getString("creator_name") + ")");
                p.setDescription(rs.getString("description"));
                p.setPublic(true);
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Playlist getPlaylistById(int playlistId) throws Exception {

        Playlist playlist = null;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM playlists WHERE id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, playlistId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setUserId(rs.getInt("user_id"));
                playlist.setName(rs.getString("name"));
                playlist.setDescription(rs.getString("description"));
                playlist.setPublic(rs.getInt("ispublic") == 1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playlist;
    }
}
