package com.revplay.dao.impl;

import com.revplay.dao.UserDAO;
import com.revplay.model.User;
import com.revplay.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAOImpl implements UserDAO {

    // ================= REGISTER =================
    @Override
    public void register(User user) throws Exception {

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO users (id, username, email, password, role) " +
                     "VALUES (users_seq.NEXTVAL, ?, ?, ?, ?)")) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            ps.executeUpdate();
        }
    }

    // ================= LOGIN =================
    @Override
    public User login(String email, String password) throws Exception {

        User user = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT id, username, email, password, role " +
                     "FROM users WHERE email = ? AND password = ?")) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                }
            }
        }
        return user;
    }

    // ================= CHANGE PASSWORD =================
    @Override
    public void changePassword(int userId, String oldPassword, String newPassword)
            throws Exception {

        String sql =
            "UPDATE users SET password = ? WHERE id = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setInt(2, userId);
            ps.setString(3, oldPassword);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new Exception("Old password is incorrect.");
            }
        }
    }
}
