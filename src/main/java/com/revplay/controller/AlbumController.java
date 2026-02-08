package com.revplay.controller;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.revplay.model.Album;
import com.revplay.service.AlbumService;
import com.revplay.service.impl.AlbumServiceImpl;
import com.revplay.util.LoggedInUser;

public class AlbumController {

    private AlbumService albumService = new AlbumServiceImpl();
    private Scanner sc = new Scanner(System.in);

    // ================= CREATE ALBUM =================
    public void createAlbum() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        sc.nextLine(); // clear buffer

        Album album = new Album();
        album.setArtistId(artistId);

        System.out.print("Enter album title: ");
        album.setTitle(sc.nextLine());

        System.out.print("Enter release date (yyyy-mm-dd): ");
        String dateStr = sc.nextLine();
        album.setReleaseDate(Date.valueOf(dateStr));

        albumService.createAlbum(album);

        System.out.println("Album created successfully!");
    }

    // ================= VIEW MY ALBUMS =================
    public void viewMyAlbums() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        List<Album> albums = albumService.getAlbumsByArtist(artistId);

        if (albums.isEmpty()) {
            System.out.println("You have not created any albums yet.");
            return;
        }

        System.out.println("\n===== MY ALBUMS =====");
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    // ================= DELETE ALBUM =================
    public void deleteAlbum() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        System.out.print("Enter album ID to delete: ");
        int albumId = sc.nextInt();

        albumService.deleteAlbum(albumId, artistId);

        System.out.println("Album deleted successfully!");
    }
}
