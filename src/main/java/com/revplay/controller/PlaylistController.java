package com.revplay.controller;

import java.util.List;
import java.util.Scanner;

import com.revplay.model.Playlist;
import com.revplay.service.PlaylistService;
import com.revplay.service.impl.PlaylistServiceImpl;

public class PlaylistController {

    private PlaylistService service = new PlaylistServiceImpl();
    private Scanner sc = new Scanner(System.in);


    // ===== CREATE PLAYLIST =====
    public void createPlaylist() throws Exception {

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();
        sc.nextLine(); // clear buffer

        System.out.print("Playlist name: ");
        String name = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        service.createPlaylist(userId, name, desc);
    }


    // ===== VIEW PLAYLISTS =====
    public void viewPlaylists() throws Exception {

        System.out.print("Enter user id: ");
        int userId = sc.nextInt();

        List<Playlist> list = service.viewPlaylists(userId);

        System.out.println("\n==== YOUR PLAYLISTS ====");

        for (Playlist p : list) {
            System.out.println(p);
        }
    }


    // ===== DELETE PLAYLIST =====
    public void deletePlaylist() throws Exception {

        System.out.print("Enter playlist id: ");
        int id = sc.nextInt();

        service.deletePlaylist(id);
    }
}
