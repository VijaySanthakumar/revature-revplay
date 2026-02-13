package com.revplay.controller;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revplay.model.Song;
import com.revplay.service.FavoriteService;
import com.revplay.service.impl.FavoriteServiceImpl;
import com.revplay.util.LoggedInUser;

public class FavoriteController {

    private FavoriteService service = new FavoriteServiceImpl();
    private Scanner sc = new Scanner(System.in);

    // ===== ADD FAVORITE =====
    public void addFavorite() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter song id: ");
        int songId = sc.nextInt();

        service.addFavorite(userId, songId);
        System.out.println("Added to favorites!");
    }

    // ===== REMOVE FAVORITE =====
    public void removeFavorite() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter song id: ");
        int songId = sc.nextInt();

        service.removeFavorite(userId, songId);
        System.out.println("Removed from favorites!");
    }

    // ===== VIEW FAVORITES =====
    public void viewFavorites() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        List<Song> songs = service.viewFavorites(userId);

        System.out.println("\n===== YOUR FAVORITES =====");

        if (songs.isEmpty()) {
            System.out.println("No favorites yet.");
            return;
        }

        for (Song s : songs) {
            System.out.println(
                s.getId() + " | " +
                s.getTitle() + " | " +
                s.getGenre() + " | " +
                s.getDuration() + " sec"
            );
        }
    }

    // ===== VIEW USERS WHO FAVORITED MY SONGS (ARTIST ONLY) =====
    public void viewUsersWhoFavoritedMySongs() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        Map<String, List<String>> result = service.getUsersWhoFavoritedArtistSongs(artistId);

        System.out.println("\n===== USERS WHO FAVORITED YOUR SONGS =====");

        if (result.isEmpty()) {
            System.out.println("No users have favorited your songs yet.");
            return;
        }

        for (Map.Entry<String, List<String>> entry : result.entrySet()) {
            System.out.println("\nSong: " + entry.getKey());
            System.out.println("  Favorited by:");
            for (String userInfo : entry.getValue()) {
                System.out.println("    - " + userInfo);
            }
        }
    }
}
