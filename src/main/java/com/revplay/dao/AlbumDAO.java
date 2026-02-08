package com.revplay.dao;

import java.util.List;
import com.revplay.model.Album;

public interface AlbumDAO {

    // Create new album (artist only)
    void createAlbum(Album album) throws Exception;

    // Get all albums by an artist
    List<Album> getAlbumsByArtist(int artistId) throws Exception;

    // Get album by ID
    Album getAlbumById(int albumId) throws Exception;

    // Delete album (artist only)
    void deleteAlbum(int albumId, int artistId) throws Exception;
}
