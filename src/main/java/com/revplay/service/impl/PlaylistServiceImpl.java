package com.revplay.service.impl;

import java.util.List;

import com.revplay.dao.PlaylistDAO;
import com.revplay.dao.impl.PlaylistDAOImpl;
import com.revplay.model.Playlist;
import com.revplay.service.PlaylistService;

public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDAO dao = new PlaylistDAOImpl();

    @Override
    public void createPlaylist(int userId, String name, String description) throws Exception {
        dao.createPlaylist(userId, name, description);
    }

    @Override
    public List<Playlist> viewPlaylists(int userId) throws Exception {
        return dao.getUserPlaylists(userId);
    }

    @Override
    public void deletePlaylist(int playlistId) throws Exception {
        dao.deletePlaylist(playlistId);
    }
}
