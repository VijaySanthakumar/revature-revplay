package com.revplay.service;

import com.revplay.model.User;

public interface UserService {

    void register(User user) throws Exception;

    User login(String email, String password) throws Exception;

    // CHANGE PASSWORD
    void changePassword(int userId, String oldPassword, String newPassword)
            throws Exception;

    // FORGOT PASSWORD
    String getSecurityQuestion(String email) throws Exception;

    boolean resetPassword(String email, String securityAnswer, String newPassword) throws Exception;
}
