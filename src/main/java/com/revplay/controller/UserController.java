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

    // ================= USER REGISTRATION =================
    public void register() throws Exception {

        User user = new User();

        System.out.print("Enter username: ");
        user.setUsername(sc.next());

        System.out.print("Enter email: ");
        user.setEmail(sc.next());

        System.out.print("Enter password: ");
        user.setPassword(sc.next());

        sc.nextLine(); // clear buffer

        System.out.print("Enter security question (for password recovery): ");
        user.setSecurityQuestion(sc.nextLine());

        System.out.print("Enter security answer: ");
        user.setSecurityAnswer(sc.nextLine());

        // default role
        user.setRole("USER");

        service.register(user);

        logger.info("New user registered: " + user.getEmail());
        System.out.println("User Registered Successfully!");
    }

    // ================= ARTIST REGISTRATION =================
    public void registerArtist() throws Exception {

        User user = new User();

        System.out.print("Enter username: ");
        user.setUsername(sc.next());

        System.out.print("Enter email: ");
        user.setEmail(sc.next());

        System.out.print("Enter password: ");
        user.setPassword(sc.next());

        sc.nextLine(); // clear buffer

        System.out.print("Enter security question (for password recovery): ");
        user.setSecurityQuestion(sc.nextLine());

        System.out.print("Enter security answer: ");
        user.setSecurityAnswer(sc.nextLine());

        // artist role
        user.setRole("ARTIST");

        service.register(user);

        logger.info("New artist registered: " + user.getEmail());
        System.out.println("Artist Registered Successfully! Please login to create your artist profile.");
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

    // ================= FORGOT PASSWORD =================
    public void forgotPassword() throws Exception {

        System.out.print("Enter your email: ");
        String email = sc.next();
        sc.nextLine(); // clear buffer

        // Get security question
        String securityQuestion = service.getSecurityQuestion(email);

        if (securityQuestion == null) {
            System.out.println("Email not found!");
            return;
        }

        System.out.println("Security Question: " + securityQuestion);
        System.out.print("Enter your answer: ");
        String answer = sc.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = sc.next();

        boolean success = service.resetPassword(email, answer, newPassword);

        if (success) {
            System.out.println("Password reset successful!");
            logger.info("Password reset for email: " + email);
        } else {
            System.out.println("Security answer incorrect. Password reset failed.");
            logger.warn("Failed password reset attempt for email: " + email);
        }
    }

    // ================= LOGOUT =================
    public void logout() {

        LoggedInUser.currentUserId = 0;
        LoggedInUser.currentUserRole = null;

        System.out.println("Logged out successfully!");
    }

    // ================= CHANGE PASSWORD =================
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
