package com.revplay.util;

public class LoggedInUser {

    // ===== SESSION STATE =====
    public static int currentUserId = 0;
    public static String currentUserRole = null;

    // ===== HELPERS =====
    public static boolean isLoggedIn() {
        return currentUserId > 0;
    }

    public static boolean isArtist() {
        return "ARTIST".equalsIgnoreCase(currentUserRole);
    }

    public static boolean isUser() {
        return "USER".equalsIgnoreCase(currentUserRole);
    }

    public static void clear() {
        currentUserId = 0;
        currentUserRole = null;
    }
}
