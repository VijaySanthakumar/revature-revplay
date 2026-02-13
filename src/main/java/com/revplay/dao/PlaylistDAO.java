package com.revplay.dao;

import java.util.List;
import com.revplay.model.Playlist;
import com.revplay.model.PlaylistSong;
import com.revplay.model.Song;

public interface PlaylistDAO {

    void createPlaylist(int userId, String name, String description, boolean isPublic) throws Exception;

    List<Playlist> getUserPlaylists(int userId) throws Exception;

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
