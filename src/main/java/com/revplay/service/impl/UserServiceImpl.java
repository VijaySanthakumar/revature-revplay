package com.revplay.service.impl;

import com.revplay.dao.UserDAO;
import com.revplay.dao.impl.UserDAOImpl;
import com.revplay.model.User;
import com.revplay.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void register(User user) throws Exception {
        userDAO.register(user);
    }

    @Override
    public User login(String email, String password) throws Exception {
        return userDAO.login(email, password);
    }

    // ================= CHANGE PASSWORD =================
    @Override
    public void changePassword(int userId,
                               String oldPassword,
                               String newPassword) throws Exception {

        if (newPassword == null || newPassword.length() < 4) {
            throw new Exception("New password must be at least 4 characters.");
        }

        userDAO.changePassword(userId, oldPassword, newPassword);
    }

    // ================= GET SECURITY QUESTION =================
    @Override
    public String getSecurityQuestion(String email) throws Exception {
        return userDAO.getSecurityQuestion(email);
    }

    // ================= RESET PASSWORD =================
    @Override
    public boolean resetPassword(String email, String securityAnswer, String newPassword) throws Exception {

        if (newPassword == null || newPassword.length() < 4) {
            throw new Exception("New password must be at least 4 characters.");
        }

        return userDAO.resetPassword(email, securityAnswer, newPassword);
    }
}
