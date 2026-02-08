package com.revplay.service;

import java.util.List;
import com.revplay.model.Song;

public interface SongService {

    // ===== CREATE =====
    void addSong(Song song) throws Exception;

    // ===== READ =====
    List<Song> getAllSongs() throws Exception;

    List<Song> getSongsByArtist(int artistId) throws Exception;

    Song getSongById(int songId) throws Exception;

    List<Song> searchSongs(String keyword) throws Exception;

    // ===== UPDATE =====
    void updateSong(Song song) throws Exception;

    void playSong(int songId) throws Exception;

    // ===== DELETE =====
    void deleteSong(int songId, int artistId) throws Exception;
}
