package com.revplay.service;

import java.util.List;
import com.revplay.model.Playlist;
import com.revplay.model.Song;

public interface PlaylistService {

    void createPlaylist(int userId, String name, String description, boolean isPublic) throws Exception;

    List<Playlist> viewPlaylists(int userId) throws Exception;

    void deletePlaylist(int playlistId) throws Exception;

    // UPDATE PLAYLIST
    void updatePlaylist(int playlistId, String name, String description, boolean isPublic) throws Exception;

    // PLAYLIST SONGS
    void addSongToPlaylist(int playlistId, int songId) throws Exception;

    void removeSongFromPlaylist(int playlistId, int songId) throws Exception;

    List<Song> getSongsInPlaylist(int playlistId) throws Exception;

    // PUBLIC PLAYLISTS
    List<Playlist> getPublicPlaylists() throws Exception;

    Playlist getPlaylistById(int playlistId) throws Exception;
}
