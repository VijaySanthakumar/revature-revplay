package com.revplay.controller;

import java.util.List;
import java.util.Scanner;

import com.revplay.model.Song;
import com.revplay.service.FavoriteService;
import com.revplay.service.impl.FavoriteServiceImpl;

public class FavoriteController {

    private FavoriteService service = new FavoriteServiceImpl();
    private Scanner sc = new Scanner(System.in);


    // ===== ADD FAVORITE =====
    public void addFavorite() throws Exception {

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        System.out.print("Enter song id: ");
        int songId = sc.nextInt();

        service.addFavorite(userId, songId);
    }


    // ===== REMOVE FAVORITE =====
    public void removeFavorite() throws Exception {

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        System.out.print("Enter song id: ");
        int songId = sc.nextInt();

        service.removeFavorite(userId, songId);
    }


    // ===== VIEW FAVORITES =====
    public void viewFavorites() throws Exception {

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        List<Song> songs = service.viewFavorites(userId);

        System.out.println("\n===== FAVORITES =====");

        for (Song s : songs) {
            System.out.println(
                s.getId() + " | " +
                s.getTitle() + " | " +
                s.getGenre() + " | " +
                s.getDuration() + " sec"
            );
        }
    }

}
