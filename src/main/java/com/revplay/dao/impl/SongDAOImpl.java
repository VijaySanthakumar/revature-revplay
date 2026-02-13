package com.revplay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revplay.dao.SongDAO;
import com.revplay.model.Song;
import com.revplay.util.DBConnection;

public class SongDAOImpl implements SongDAO {

    private static final String INSERT_SONG =
        "INSERT INTO songs (title, genre, duration, play_count, artist_id, album_id) " +
        "VALUES (?, ?, ?, 0, ?, ?)";

    private static final String SELECT_ALL =
        "SELECT id, title, genre, duration, play_count, artist_id, album_id FROM songs";

    private static final String DELETE_SONG =
        "DELETE FROM songs WHERE id = ? AND artist_id = ?";

    private static final String INCREMENT_PLAY =
        "UPDATE songs SET play_count = play_count + 1 WHERE id = ?";

    private static final String SEARCH_SONGS =
        "SELECT id, title, genre, duration, play_count, artist_id, album_id " +
        "FROM songs WHERE LOWER(title) LIKE ? OR LOWER(genre) LIKE ?";

    private static final String SELECT_BY_ARTIST =
        "SELECT id, title, genre, duration, play_count, album_id " +
        "FROM songs WHERE artist_id = ?";

    private static final String UPDATE_SONG =
        "UPDATE songs SET title = ?, genre = ?, duration = ?, album_id = ? " +
        "WHERE id = ? AND artist_id = ?";
    

    // ================= ADD SONG =================
    @Override
    public void addSong(Song song) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SONG)) {

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getGenre());
            ps.setInt(3, song.getDuration());
            ps.setInt(4, song.getArtistId());

            // ✅ CRITICAL FIX: album_id handling
            if (song.getAlbumId() != null) {
                ps.setInt(5, song.getAlbumId());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER);
            }


            ps.executeUpdate();
        }
    }


    // ================= GET ALL SONGS =================
    @Override
    public List<Song> getAllSongs() throws Exception {

        List<Song> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                s.setArtistId(rs.getInt("artist_id"));
                s.setAlbumId(rs.getInt("album_id"));
                list.add(s);
            }
        }

        return list;
    }

    // ================= DELETE SONG (OWNER ONLY) =================
    @Override
    public void deleteSong(int songId, int artistId) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_SONG)) {

            ps.setInt(1, songId);
            ps.setInt(2, artistId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Song not found or you are not the owner.");
            }
        }
    }

    // ================= PLAY SONG =================
    @Override
    public void incrementPlayCount(int songId) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INCREMENT_PLAY)) {

            ps.setInt(1, songId);
            ps.executeUpdate();
        }
    }

    // ================= SONGS BY ARTIST =================
    @Override
    public List<Song> getSongsByArtistId(int artistId) throws Exception {

        List<Song> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_BY_ARTIST)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                s.setAlbumId(rs.getInt("album_id"));
                list.add(s);
            }
        }

        return list;
    }

    // ================= SEARCH SONGS =================
    @Override
    public List<Song> searchSongs(String keyword) throws Exception {

        List<Song> list = new ArrayList<>();
        String like = "%" + keyword.toLowerCase() + "%";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SEARCH_SONGS)) {

            ps.setString(1, like);
            ps.setString(2, like);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                s.setArtistId(rs.getInt("artist_id"));
                s.setAlbumId(rs.getInt("album_id"));
                list.add(s);
            }
        }

        return list;
    }

    // ================= UPDATE SONG =================
    @Override
    public void updateSong(Song song) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_SONG)) {

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getGenre());
            ps.setInt(3, song.getDuration());

            if (song.getAlbumId() > 0) {
                ps.setInt(4, song.getAlbumId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.setInt(5, song.getId());
            ps.setInt(6, song.getArtistId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("Song not found or you are not the owner.");
            }
        }
    }

    // ================= GET SONG BY ID =================
    @Override
    public Song getSongById(int songId) throws Exception {

        Song song = null;

        String sql =
            "SELECT id, title, genre, duration, play_count, artist_id, album_id " +
            "FROM songs WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, songId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                song = new Song();
                song.setId(rs.getInt("id"));
                song.setTitle(rs.getString("title"));
                song.setGenre(rs.getString("genre"));
                song.setDuration(rs.getInt("duration"));
                song.setPlayCount(rs.getInt("play_count"));
                song.setArtistId(rs.getInt("artist_id"));
                song.setAlbumId(rs.getInt("album_id"));
            }
        }

        return song;
    }

    // ================= GET SONGS BY GENRE =================
    @Override
    public List<Song> getSongsByGenre(String genre) throws Exception {

        List<Song> list = new ArrayList<>();
        String like = "%" + genre.toLowerCase() + "%";

        String sql =
            "SELECT id, title, genre, duration, play_count, artist_id, album_id " +
            "FROM songs WHERE LOWER(genre) LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, like);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setGenre(rs.getString("genre"));
                s.setDuration(rs.getInt("duration"));
                s.setPlayCount(rs.getInt("play_count"));
                s.setArtistId(rs.getInt("artist_id"));
                s.setAlbumId(rs.getInt("album_id"));
                list.add(s);
            }
        }

        return list;
    }

    // ================= GET ALL GENRES =================
    @Override
    public List<String> getAllGenres() throws Exception {

        List<String> list = new ArrayList<>();

        String sql = "SELECT DISTINCT genre FROM songs WHERE genre IS NOT NULL";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("genre"));
            }
        }

        return list;
    }

}
