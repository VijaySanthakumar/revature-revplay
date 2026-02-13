package com.revplay.service.impl;

import java.util.List;

import com.revplay.dao.PlaylistDAO;
import com.revplay.dao.impl.PlaylistDAOImpl;
import com.revplay.model.Playlist;
import com.revplay.model.Song;
import com.revplay.service.PlaylistService;

public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistDAO dao = new PlaylistDAOImpl();

    @Override
    public void createPlaylist(int userId, String name, String description, boolean isPublic) throws Exception {
        dao.createPlaylist(userId, name, description, isPublic);
    }

    @Override
    public List<Playlist> viewPlaylists(int userId) throws Exception {
        return dao.getUserPlaylists(userId);
    }

    @Override
    public void deletePlaylist(int playlistId) throws Exception {
        dao.deletePlaylist(playlistId);
    }

    @Override
    public void updatePlaylist(int playlistId, String name, String description, boolean isPublic) throws Exception {
        dao.updatePlaylist(playlistId, name, description, isPublic);
    }

    @Override
    public void addSongToPlaylist(int playlistId, int songId) throws Exception {
        dao.addSongToPlaylist(playlistId, songId);
    }

    @Override
    public void removeSongFromPlaylist(int playlistId, int songId) throws Exception {
        dao.removeSongFromPlaylist(playlistId, songId);
    }

    @Override
    public List<Song> getSongsInPlaylist(int playlistId) throws Exception {
        return dao.getSongsInPlaylist(playlistId);
    }

    @Override
    public List<Playlist> getPublicPlaylists() throws Exception {
        return dao.getPublicPlaylists();
    }

    @Override
    public Playlist getPlaylistById(int playlistId) throws Exception {
        return dao.getPlaylistById(playlistId);
    }
}
