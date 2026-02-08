package com.revplay.service;

import java.util.List;
import com.revplay.model.Favorite;
import com.revplay.model.Song;




public interface FavoriteService {

    void addFavorite(int userId, int songId) throws Exception;

    void removeFavorite(int userId, int songId) throws Exception;

    List<Favorite> getUserFavorites(int userId) throws Exception;
    
    List<Song> viewFavorites(int userId) throws Exception;

}
