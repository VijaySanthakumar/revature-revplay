package com.revplay.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revplay.dao.ListeningHistoryDAO;
import com.revplay.model.ListeningHistory;
import com.revplay.util.DBConnection;

public class ListeningHistoryDAOImpl implements ListeningHistoryDAO {

    private static final String INSERT_HISTORY =
        "INSERT INTO listening_history (user_id, song_id) VALUES (?, ?)";

    private static final String SELECT_HISTORY =
        "SELECT lh.user_id, lh.song_id, s.title, lh.played_at " +
        "FROM listening_history lh " +
        "JOIN songs s ON lh.song_id = s.id " +
        "WHERE lh.user_id = ? " +
        "ORDER BY lh.played_at DESC";

    @Override
    public void addHistory(int userId, int songId) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_HISTORY)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.executeUpdate();
        }
    }

    @Override
    public List<ListeningHistory> getHistoryByUser(int userId) throws Exception {

        List<ListeningHistory> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SELECT_HISTORY)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ListeningHistory h = new ListeningHistory();
                h.setUserId(rs.getInt("user_id"));
                h.setSongId(rs.getInt("song_id"));
                h.setSongTitle(rs.getString("title")); // ✅ key line
                h.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(h);
            }
        }

        return list;
    }
}
