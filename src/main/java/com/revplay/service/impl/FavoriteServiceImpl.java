package com.revplay.service.impl;

import com.revplay.dao.FavoriteDAO;
import com.revplay.dao.impl.FavoriteDAOImpl;
import com.revplay.model.Favorite;
import com.revplay.service.FavoriteService;
import com.revplay.model.Song;
import java.util.List;
import java.util.Map;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDAO dao = new FavoriteDAOImpl();

    @Override
    public void addFavorite(int userId, int songId) throws Exception {
        dao.addFavorite(userId, songId);
    }

    @Override
    public void removeFavorite(int userId, int songId) throws Exception {
        dao.removeFavorite(userId, songId);
    }

    @Override
    public List<Favorite> getUserFavorites(int userId) throws Exception {
        return dao.getUserFavorites(userId);
    }

    @Override
    public List<Song> viewFavorites(int userId) throws Exception {
        return dao.viewFavorites(userId);
    }

    @Override
    public Map<String, List<String>> getUsersWhoFavoritedArtistSongs(int artistId) throws Exception {
        return dao.getUsersWhoFavoritedArtistSongs(artistId);
    }

}
