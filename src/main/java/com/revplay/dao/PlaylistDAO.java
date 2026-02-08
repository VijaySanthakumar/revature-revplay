package com.revplay.dao;

import java.util.List;
import com.revplay.model.Playlist;

public interface PlaylistDAO {

    void createPlaylist(int userId, String name, String description) throws Exception;

    List<Playlist> getUserPlaylists(int userId) throws Exception;

    void deletePlaylist(int playlistId) throws Exception;
}
