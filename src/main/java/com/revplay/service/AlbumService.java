package com.revplay.service;

import java.util.List;
import com.revplay.model.Album;

public interface AlbumService {

    void createAlbum(Album album) throws Exception;

    List<Album> getAlbumsByArtist(int artistId) throws Exception;

    Album getAlbumById(int albumId) throws Exception;

    void deleteAlbum(int albumId, int artistId) throws Exception;
}
