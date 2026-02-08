package com.revplay.service.impl;

import java.util.List;

import com.revplay.dao.AlbumDAO;
import com.revplay.dao.impl.AlbumDAOImpl;
import com.revplay.model.Album;
import com.revplay.service.AlbumService;

public class AlbumServiceImpl implements AlbumService {

    private AlbumDAO albumDAO = new AlbumDAOImpl();

    @Override
    public void createAlbum(Album album) throws Exception {
        albumDAO.createAlbum(album);
    }

    @Override
    public List<Album> getAlbumsByArtist(int artistId) throws Exception {
        return albumDAO.getAlbumsByArtist(artistId);
    }

    @Override
    public Album getAlbumById(int albumId) throws Exception {
        return albumDAO.getAlbumById(albumId);
    }

    @Override
    public void deleteAlbum(int albumId, int artistId) throws Exception {
        albumDAO.deleteAlbum(albumId, artistId);
    }
}
