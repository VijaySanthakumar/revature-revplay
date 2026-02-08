package com.revplay.controller;

import com.revplay.model.ArtistProfile;
import com.revplay.service.ArtistProfileService;
import com.revplay.service.impl.ArtistProfileServiceImpl;
import com.revplay.util.LoggedInUser;

import java.util.Scanner;

public class ArtistProfileController {

    private ArtistProfileService service = new ArtistProfileServiceImpl();
    private Scanner sc = new Scanner(System.in);

    // ===== CREATE ARTIST PROFILE =====
    public void createProfile() {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        try {
            ArtistProfile profile = new ArtistProfile();
            profile.setArtistId(artistId);

            sc.nextLine(); // clear scanner buffer

            System.out.print("Enter artist bio: ");
            profile.setBio(sc.nextLine());

            System.out.print("Enter genre: ");
            profile.setGenre(sc.nextLine());

            System.out.print("Enter social media links: ");
            profile.setSocialLinks(sc.nextLine());

            service.createProfile(profile);

            System.out.println("Artist profile created successfully!");

        } catch (Exception e) {
            // ✅ THIS PREVENTS ORA-00001 CRASH
            System.out.println(e.getMessage());
        }
    }

    // ===== VIEW ARTIST PROFILE =====
    public void viewProfile() {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        try {
            ArtistProfile profile = service.getProfileByArtistId(artistId);

            if (profile == null) {
                System.out.println("No artist profile found.");
                return;
            }

            System.out.println("\n===== ARTIST PROFILE =====");
            System.out.println(profile);

        } catch (Exception e) {
            System.out.println("Error fetching artist profile.");
        }
    }
 // ===== UPDATE ARTIST PROFILE =====
    public void updateProfile() {

        int artistId = LoggedInUser.currentUserId;

        if (artistId <= 0) {
            System.out.println("Please login first.");
            return;
        }

        try {
            ArtistProfile existing =
                    service.getProfileByArtistId(artistId);

            if (existing == null) {
                System.out.println("No artist profile found to update.");
                return;
            }

            ArtistProfile profile = new ArtistProfile();
            profile.setArtistId(artistId);

            sc.nextLine(); // clear buffer

            System.out.print("Enter new bio: ");
            profile.setBio(sc.nextLine());

            System.out.print("Enter new genre: ");
            profile.setGenre(sc.nextLine());

            System.out.print("Enter new social media links: ");
            profile.setSocialLinks(sc.nextLine());

            service.updateProfile(profile);

            System.out.println("Artist profile updated successfully!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
