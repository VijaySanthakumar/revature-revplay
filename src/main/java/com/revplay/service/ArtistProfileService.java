package com.revplay.service;

import com.revplay.model.ArtistProfile;

public interface ArtistProfileService {

    void createProfile(ArtistProfile profile) throws Exception;
    void updateProfile(ArtistProfile profile) throws Exception;


    ArtistProfile getProfileByArtistId(int artistId) throws Exception;
    
    
}
