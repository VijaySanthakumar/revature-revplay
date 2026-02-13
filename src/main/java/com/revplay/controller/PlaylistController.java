package com.revplay.controller;

import java.util.List;
import java.util.Scanner;

import com.revplay.model.Playlist;
import com.revplay.model.Song;
import com.revplay.service.PlaylistService;
import com.revplay.service.impl.PlaylistServiceImpl;
import com.revplay.util.LoggedInUser;

public class PlaylistController {

    private PlaylistService service = new PlaylistServiceImpl();
    private Scanner sc = new Scanner(System.in);

    // ===== CREATE PLAYLIST =====
    public void createPlaylist() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        sc.nextLine(); // clear buffer

        System.out.print("Playlist name: ");
        String name = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Make playlist public? (yes/no): ");
        String isPublicStr = sc.nextLine();
        boolean isPublic = isPublicStr.equalsIgnoreCase("yes");

        service.createPlaylist(userId, name, desc, isPublic);
        System.out.println("Playlist created successfully!");
    }

    // ===== VIEW MY PLAYLISTS =====
    public void viewPlaylists() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        List<Playlist> list = service.viewPlaylists(userId);

        System.out.println("\n==== YOUR PLAYLISTS ====");

        if (list.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }

        for (Playlist p : list) {
            System.out.println(p);
        }
    }

    // ===== UPDATE PLAYLIST =====
    public void updatePlaylist() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter playlist ID to update: ");
        int playlistId = sc.nextInt();
        sc.nextLine(); // clear buffer

        // Verify ownership
        Playlist playlist = service.getPlaylistById(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        if (playlist.getUserId() != userId) {
            System.out.println("You can only update your own playlists.");
            return;
        }

        System.out.print("New name: ");
        String name = sc.nextLine();

        System.out.print("New description: ");
        String desc = sc.nextLine();

        System.out.print("Make public? (yes/no): ");
        String isPublicStr = sc.nextLine();
        boolean isPublic = isPublicStr.equalsIgnoreCase("yes");

        service.updatePlaylist(playlistId, name, desc, isPublic);
        System.out.println("Playlist updated successfully!");
    }

    // ===== ADD SONG TO PLAYLIST =====
    public void addSongToPlaylist() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter playlist ID: ");
        int playlistId = sc.nextInt();

        // Verify ownership
        Playlist playlist = service.getPlaylistById(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        if (playlist.getUserId() != userId) {
            System.out.println("You can only add songs to your own playlists.");
            return;
        }

        System.out.print("Enter song ID to add: ");
        int songId = sc.nextInt();

        service.addSongToPlaylist(playlistId, songId);
    }

    // ===== REMOVE SONG FROM PLAYLIST =====
    public void removeSongFromPlaylist() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter playlist ID: ");
        int playlistId = sc.nextInt();

        // Verify ownership
        Playlist playlist = service.getPlaylistById(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        if (playlist.getUserId() != userId) {
            System.out.println("You can only remove songs from your own playlists.");
            return;
        }

        System.out.print("Enter song ID to remove: ");
        int songId = sc.nextInt();

        service.removeSongFromPlaylist(playlistId, songId);
    }

    // ===== VIEW SONGS IN PLAYLIST =====
    public void viewSongsInPlaylist() throws Exception {

        System.out.print("Enter playlist ID: ");
        int playlistId = sc.nextInt();

        Playlist playlist = service.getPlaylistById(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }

        // Check if user can view this playlist
        int userId = LoggedInUser.currentUserId;
        if (!playlist.isPublic() && playlist.getUserId() != userId) {
            System.out.println("This is a private playlist.");
            return;
        }

        List<Song> songs = service.getSongsInPlaylist(playlistId);

        System.out.println("\n==== SONGS IN PLAYLIST: " + playlist.getName() + " ====");

        if (songs.isEmpty()) {
            System.out.println("No songs in this playlist.");
            return;
        }

        for (Song s : songs) {
            System.out.println(s.getId() + " | " + s.getTitle() + " | " + s.getGenre() + " | " + s.getDuration() + " sec");
        }
    }

    // ===== VIEW PUBLIC PLAYLISTS =====
    public void viewPublicPlaylists() throws Exception {

        List<Playlist> list = service.getPublicPlaylists();

        System.out.println("\n==== PUBLIC PLAYLISTS ====");

        if (list.isEmpty()) {
            System.out.println("No public playlists found.");
            return;
        }

        for (Playlist p : list) {
            System.out.println(p);
        }
    }

    // ===== DELETE PLAYLIST =====
    public void deletePlaylist() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter playlist ID to delete: ");
        int playlistId = sc.nextInt();

        // Verify ownership
        Playlist playlist = service.getPlaylistById(playlistId);
        if (playlist == null) {
            System.out.println("Playlist not found.");
            return;
        }
        if (playlist.getUserId() != userId) {
            System.out.println("You can only delete your own playlists.");
            return;
        }

        service.deletePlaylist(playlistId);
        System.out.println("Playlist deleted successfully!");
    }
}
