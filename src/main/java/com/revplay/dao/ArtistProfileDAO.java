package com.revplay.dao;

import com.revplay.model.ArtistProfile;

public interface ArtistProfileDAO {

    void createProfile(ArtistProfile profile) throws Exception;
    void updateProfile(ArtistProfile profile) throws Exception;


    ArtistProfile getProfileByArtistId(int artistId) throws Exception;
}
