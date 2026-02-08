package com.revplay.dao;

import com.revplay.model.User;

public interface UserDAO {

    void register(User user) throws Exception;

    User login(String email, String password) throws Exception;
    
    void changePassword(int userId, String oldPassword, String newPassword) throws Exception;

}
