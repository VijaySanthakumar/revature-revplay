package com.revplay.controller;

import java.util.List;
import java.util.Scanner;

import com.revplay.model.Album;
import com.revplay.model.Song;
import com.revplay.service.AlbumService;
import com.revplay.service.ListeningHistoryService;
import com.revplay.service.SongService;
import com.revplay.service.impl.AlbumServiceImpl;
import com.revplay.service.impl.ListeningHistoryServiceImpl;
import com.revplay.service.impl.SongServiceImpl;
import com.revplay.util.LoggedInUser;

public class SongController {

    private SongService songService = new SongServiceImpl();
    private ListeningHistoryService historyService =
            new ListeningHistoryServiceImpl();

    private Scanner sc = new Scanner(System.in);

    // ===== MUSIC PLAYER STATE =====
    private boolean isPlaying = false;
    private boolean repeatOn = false;
    private int currentSongId = -1;

    // ========= ADD SONG (ARTIST ONLY, WITH ALBUM SUPPORT) =========
    public void addSong() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist to upload songs.");
            return;
        }

        Song song = new Song();
        song.setArtistId(artistId);

        sc.nextLine(); // clear buffer

        System.out.print("Enter song title: ");
        song.setTitle(sc.nextLine());

        System.out.print("Enter genre: ");
        song.setGenre(sc.nextLine());

        System.out.print("Enter duration (seconds): ");
        song.setDuration(sc.nextInt());

        // ===== ALBUM CHOICE =====
        sc.nextLine();
        System.out.print("Add this song to an album? (yes/no): ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {

            AlbumService albumService = new AlbumServiceImpl();
            List<Album> albums = albumService.getAlbumsByArtist(artistId);

            if (albums.isEmpty()) {
                System.out.println("You have no albums. Song will be uploaded without album.");
                song.setAlbumId(null);
            } else {
                System.out.println("\n===== YOUR ALBUMS =====");
                for (Album a : albums) {
                    System.out.println(
                        "ID: " + a.getId() +
                        " | Title: " + a.getTitle() +
                        " | Release Date: " + a.getReleaseDate()
                    );
                }

                System.out.print("Enter album ID: ");
                int albumId = sc.nextInt();
                song.setAlbumId(albumId);
            }
        } else {
            song.setAlbumId(null);
        }

        songService.addSong(song);
        System.out.println("Song uploaded successfully!");
    }

    // ========= VIEW SONGS =========
    public void viewSongs() throws Exception {

        List<Song> songs = songService.getAllSongs();

        System.out.println("\n===== SONG LIST =====");
        for (Song s : songs) {
            System.out.println(
                s.getId() + " | " +
                s.getTitle() + " | " +
                s.getGenre() + " | " +
                s.getDuration() + " sec | Plays: " +
                s.getPlayCount()
            );
        }
    }

    // ========= DELETE SONG =========
    public void deleteSong() throws Exception {

        System.out.print("Enter song ID to delete: ");
        int id = sc.nextInt();

        songService.deleteSong(id, LoggedInUser.currentUserId);
        System.out.println("Song deleted successfully!");
    }

    // ========= PLAY SONG (WITH PROGRESS BAR) =========
    public void playSong() throws Exception {

        int userId = LoggedInUser.currentUserId;

        if (userId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter song ID to play: ");
        int songId = sc.nextInt();

        songService.playSong(songId);
        historyService.addHistory(userId, songId);

        currentSongId = songId;
        isPlaying = true;

        System.out.println("▶ Playing song ID: " + songId);

        // ===== SIMULATED PROGRESS BAR =====
        int totalBlocks = 20;

        for (int i = 0; i <= totalBlocks; i++) {

            if (!isPlaying) {
                System.out.println("\n⏸ Playback paused.");
                break;
            }

            String bar =
                    "█".repeat(i) +
                    "░".repeat(totalBlocks - i);

            int percent = (i * 100) / totalBlocks;

            System.out.print("\r[" + bar + "] " + percent + "%");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (isPlaying) {
            System.out.println("\n✔ Song playback finished.");
            isPlaying = false;
            currentSongId = -1;
        }
    }

    // ========= PAUSE SONG =========
    public void pauseSong() {

        if (!isPlaying) {
            System.out.println("No song is currently playing.");
            return;
        }

        isPlaying = false;
        System.out.println("⏸ Song paused.");
    }

    // ========= SKIP SONG =========
    public void skipSong() {

        if (currentSongId == -1) {
            System.out.println("No song to skip.");
            return;
        }

        if (repeatOn) {
            System.out.println("🔁 Repeat is ON. Replaying song ID: " + currentSongId);
        } else {
            isPlaying = false;
            currentSongId = -1;
            System.out.println("⏭ Song skipped.");
        }
    }

    // ========= TOGGLE REPEAT =========
    public void toggleRepeat() {

        repeatOn = !repeatOn;

        if (repeatOn) {
            System.out.println("🔁 Repeat ON");
        } else {
            System.out.println("Repeat OFF");
        }
    }

    // ========= SEARCH SONGS =========
    public void searchSongs() throws Exception {

        sc.nextLine();
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        List<Song> songs = songService.searchSongs(keyword);

        System.out.println("\n===== SEARCH RESULTS =====");
        for (Song s : songs) {
            System.out.println(
                s.getId() + " | " +
                s.getTitle() + " | " +
                s.getGenre() + " | " +
                s.getDuration() + " sec"
            );
        }
    }

    // ========= BROWSE BY GENRE =========
    public void browseByGenre() throws Exception {

        sc.nextLine();
        System.out.print("Enter genre to browse: ");
        String genre = sc.nextLine();

        List<Song> songs = songService.getSongsByGenre(genre);

        System.out.println("\n===== SONGS IN GENRE: " + genre + " =====");

        if (songs.isEmpty()) {
            System.out.println("No songs found in this genre.");
            return;
        }

        for (Song s : songs) {
            System.out.println(
                s.getId() + " | " +
                s.getTitle() + " | " +
                s.getDuration() + " sec | Plays: " + s.getPlayCount()
            );
        }
    }

    // ========= VIEW ALL GENRES =========
    public void viewAllGenres() throws Exception {

        List<String> genres = songService.getAllGenres();

        System.out.println("\n===== AVAILABLE GENRES =====");

        if (genres.isEmpty()) {
            System.out.println("No genres found.");
            return;
        }

        for (String genre : genres) {
            System.out.println("- " + genre);
        }
    }

    // ========= VIEW MY UPLOADED SONGS =========
    public void viewMySongs() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        List<Song> songs = songService.getSongsByArtist(artistId);

        if (songs.isEmpty()) {
            System.out.println("You have not uploaded any songs yet.");
            return;
        }

        System.out.println("\n===== MY UPLOADED SONGS =====");
        for (Song s : songs) {
            System.out.println(
                "ID: " + s.getId() +
                " | Title: " + s.getTitle() +
                " | Genre: " + s.getGenre() +
                " | Duration: " + s.getDuration() + "s"
            );
        }
    }

    // ========= UPDATE SONG (ARTIST ONLY) =========
    public void updateSong() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        System.out.print("Enter Song ID to update: ");
        int songId = sc.nextInt();
        sc.nextLine();

        Song existing = songService.getSongById(songId);

        if (existing == null) {
            System.out.println("Song not found.");
            return;
        }

        if (existing.getArtistId() != artistId) {
            System.out.println("You are not allowed to update this song.");
            return;
        }

        Song song = new Song();
        song.setId(songId);
        song.setArtistId(artistId);

        System.out.print("Enter new title: ");
        song.setTitle(sc.nextLine());

        System.out.print("Enter new genre: ");
        song.setGenre(sc.nextLine());

        System.out.print("Enter new duration (seconds): ");
        song.setDuration(sc.nextInt());

        sc.nextLine();
        System.out.print("Change album? (yes/no): ");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {

            AlbumService albumService = new AlbumServiceImpl();
            List<Album> albums = albumService.getAlbumsByArtist(artistId);

            if (albums.isEmpty()) {
                song.setAlbumId(null);
            } else {
                System.out.println("\n===== YOUR ALBUMS =====");
                for (Album a : albums) {
                    System.out.println("ID: " + a.getId() + " | Title: " + a.getTitle());
                }

                System.out.print("Enter album ID: ");
                int albumId = sc.nextInt();
                song.setAlbumId(albumId);
            }
        } else {
            song.setAlbumId(existing.getAlbumId());
        }

        songService.updateSong(song);
        System.out.println("Song updated successfully!");
    }

    // ========= VIEW MY SONGS STATISTICS =========
    public void viewMySongStatistics() throws Exception {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login as an artist first.");
            return;
        }

        List<Song> songs = songService.getSongsByArtist(artistId);

        if (songs.isEmpty()) {
            System.out.println("You have not uploaded any songs yet.");
            return;
        }

        System.out.println("\n===== MY SONGS STATISTICS =====");

        int totalPlays = 0;
        int totalSongs = songs.size();
        Song mostPlayed = null;

        for (Song s : songs) {
            System.out.println(
                "ID: " + s.getId() +
                " | Title: " + s.getTitle() +
                " | Genre: " + s.getGenre() +
                " | Play Count: " + s.getPlayCount()
            );
            totalPlays += s.getPlayCount();
            if (mostPlayed == null || s.getPlayCount() > mostPlayed.getPlayCount()) {
                mostPlayed = s;
            }
        }

        System.out.println("\n----- SUMMARY -----");
        System.out.println("Total Songs: " + totalSongs);
        System.out.println("Total Plays: " + totalPlays);
        System.out.println("Average Plays per Song: " + (totalSongs > 0 ? (totalPlays / totalSongs) : 0));
        if (mostPlayed != null && mostPlayed.getPlayCount() > 0) {
            System.out.println("Most Played: " + mostPlayed.getTitle() + " (" + mostPlayed.getPlayCount() + " plays)");
        }
    }
}
