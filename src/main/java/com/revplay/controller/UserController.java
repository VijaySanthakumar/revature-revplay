package com.revplay.controller;

import com.revplay.model.User;
import com.revplay.service.UserService;
import com.revplay.service.impl.UserServiceImpl;
import com.revplay.util.LoggedInUser;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class UserController {

    // Logger must be inside the class
    private static final Logger logger =
            Logger.getLogger(UserController.class);

    private UserService service = new UserServiceImpl();
    private Scanner sc = new Scanner(System.in);

    // ================= REGISTER =================
    public void register() throws Exception {

        User user = new User();

        System.out.print("Enter username: ");
        user.setUsername(sc.next());

        System.out.print("Enter email: ");
        user.setEmail(sc.next());

        System.out.print("Enter password: ");
        user.setPassword(sc.next());

        // default role
        user.setRole("USER");

        service.register(user);

        logger.info("New user registered: " + user.getEmail());
        System.out.println("User Registered Successfully!");
    }

    // ================= LOGIN =================
    public void login() throws Exception {

        System.out.print("Enter email: ");
        String email = sc.next();

        System.out.print("Enter password: ");
        String password = sc.next();

        User user = service.login(email, password);

        if (user != null) {

            // ===== SESSION SETUP =====
            LoggedInUser.currentUserId = user.getId();
            LoggedInUser.currentUserRole = user.getRole();

            logger.info("Login successful for email: " + email);

            System.out.println(
                "Login Successful! Welcome " + user.getUsername() +
                " [" + user.getRole() + "]"
            );

        } else {
            logger.warn("Login failed for email: " + email);
            System.out.println("Invalid Credentials!");
        }
    }

    // ================= LOGOUT (OPTIONAL BUT GOOD) =================
    public void logout() {

        LoggedInUser.currentUserId = 0;
        LoggedInUser.currentUserRole = null;

        System.out.println("Logged out successfully!");
    }
 // ========= CHANGE PASSWORD =========
    public void changePassword() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter old password: ");
        String oldPassword = sc.next();

        System.out.print("Enter new password: ");
        String newPassword = sc.next();

        service.changePassword(userId, oldPassword, newPassword);

        System.out.println("Password changed successfully!");
    }

}
