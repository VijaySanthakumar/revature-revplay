package com.revplay.service.impl;

import java.util.List;

import com.revplay.dao.SongDAO;
import com.revplay.dao.impl.SongDAOImpl;
import com.revplay.model.Song;
import com.revplay.service.SongService;

public class SongServiceImpl implements SongService {

    private SongDAO songDAO = new SongDAOImpl();

    // ===== ADD SONG =====
    @Override
    public void addSong(Song song) throws Exception {
        songDAO.addSong(song);
    }

    // ===== GET ALL SONGS =====
    @Override
    public List<Song> getAllSongs() throws Exception {
        return songDAO.getAllSongs();
    }

    // ===== GET SONG BY ID =====
    @Override
    public Song getSongById(int songId) throws Exception {
        return songDAO.getSongById(songId);
    }

    // ===== GET SONGS BY ARTIST =====
    @Override
    public List<Song> getSongsByArtist(int artistId) throws Exception {

        if (artistId <= 0) {
            throw new Exception("Invalid artist ID");
        }

        return songDAO.getSongsByArtistId(artistId);
    }

    // ===== SEARCH SONGS =====
    @Override
    public List<Song> searchSongs(String keyword) throws Exception {
        return songDAO.searchSongs(keyword);
    }

    // ===== BROWSE BY GENRE =====
    @Override
    public List<Song> getSongsByGenre(String genre) throws Exception {
        return songDAO.getSongsByGenre(genre);
    }

    @Override
    public List<String> getAllGenres() throws Exception {
        return songDAO.getAllGenres();
    }

    // ===== PLAY SONG =====
    @Override
    public void playSong(int songId) throws Exception {
        songDAO.incrementPlayCount(songId);
    }

    // ===== UPDATE SONG (OWNER ONLY) =====
    @Override
    public void updateSong(Song song) throws Exception {

        Song existing = songDAO.getSongById(song.getId());

        if (existing == null) {
            throw new Exception("Song not found");
        }

        // ownership check
        if (existing.getArtistId() != song.getArtistId()) {
            throw new Exception("You are not allowed to update this song");
        }

        songDAO.updateSong(song);
    }

    // ===== DELETE SONG (OWNER ONLY) =====
    @Override
    public void deleteSong(int songId, int artistId) throws Exception {
        songDAO.deleteSong(songId, artistId);
    }
}
