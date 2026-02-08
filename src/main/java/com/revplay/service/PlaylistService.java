package com.revplay.service;

import java.util.List;
import com.revplay.model.Playlist;

public interface PlaylistService {

    void createPlaylist(int userId, String name, String description) throws Exception;

    List<Playlist> viewPlaylists(int userId) throws Exception;

    void deletePlaylist(int playlistId) throws Exception;
}
