package com.revplay.dao;

import java.util.List;
import com.revplay.model.Favorite;
import com.revplay.model.Song;
import java.util.List;
import com.revplay.model.Song;



public interface FavoriteDAO {

    void addFavorite(int userId, int songId) throws Exception;

    void removeFavorite(int userId, int songId) throws Exception;

    List<Favorite> getUserFavorites(int userId) throws Exception;
    
    List<Song> viewFavorites(int userId) throws Exception;

}
