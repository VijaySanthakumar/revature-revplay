package com.revplay.service.impl;

import com.revplay.dao.ArtistProfileDAO;
import com.revplay.dao.impl.ArtistProfileDAOImpl;
import com.revplay.model.ArtistProfile;
import com.revplay.service.ArtistProfileService;

public class ArtistProfileServiceImpl implements ArtistProfileService {

    private ArtistProfileDAO artistProfileDAO = new ArtistProfileDAOImpl();

    @Override
    public void createProfile(ArtistProfile profile) throws Exception {

        if (profile.getArtistId() <= 0) {
            throw new Exception("Invalid artist ID");
        }

        // 🔒 CHECK IF PROFILE ALREADY EXISTS
        ArtistProfile existing =
                artistProfileDAO.getProfileByArtistId(profile.getArtistId());

        if (existing != null) {
            throw new Exception("Artist profile already exists");
        }

        artistProfileDAO.createProfile(profile);
    }

    @Override
    public ArtistProfile getProfileByArtistId(int artistId) throws Exception {
        return artistProfileDAO.getProfileByArtistId(artistId);
    }
    @Override
    public void updateProfile(ArtistProfile profile) throws Exception {

        int artistId = profile.getArtistId();

        if (artistId <= 0) {
            throw new Exception("Invalid artist ID");
        }

        // profile must already exist
        ArtistProfile existing =
                artistProfileDAO.getProfileByArtistId(artistId);

        if (existing == null) {
            throw new Exception("Artist profile does not exist");
        }

        artistProfileDAO.updateProfile(profile);
    }

}
