package com.revplay.dao;

import java.util.List;
import com.revplay.model.Song;

public interface SongDAO {

    // ===== CREATE =====
    void addSong(Song song) throws Exception;

    // ===== READ =====
    List<Song> getAllSongs() throws Exception;

    List<Song> getSongsByArtistId(int artistId) throws Exception;

    Song getSongById(int songId) throws Exception;

    List<Song> searchSongs(String keyword) throws Exception;

    // ===== UPDATE =====
    void updateSong(Song song) throws Exception;

    void incrementPlayCount(int songId) throws Exception;

    // ===== DELETE (OWNER ONLY) =====
    void deleteSong(int songId, int artistId) throws Exception;
}
