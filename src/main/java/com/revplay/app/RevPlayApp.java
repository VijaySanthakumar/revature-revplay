package com.revplay.app;

import java.util.Scanner;

import com.revplay.controller.UserController;
import com.revplay.controller.SongController;
import com.revplay.controller.FavoriteController;
import com.revplay.controller.PlaylistController;
import com.revplay.controller.ListeningHistoryController;
import com.revplay.controller.ArtistProfileController;
import com.revplay.controller.AlbumController;
import com.revplay.controller.PodcastController;
import com.revplay.util.LoggedInUser;


public class RevPlayApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController();
        SongController songController = new SongController();
        FavoriteController favoriteController = new FavoriteController();
        PlaylistController playlistController = new PlaylistController();
        ListeningHistoryController historyController = new ListeningHistoryController();
        ArtistProfileController artistController = new ArtistProfileController();
        AlbumController albumController = new AlbumController();
        PodcastController podcastController = new PodcastController();

        while (true) {
            int choice;

            if (!LoggedInUser.isLoggedIn()) {
                // ==================== PUBLIC MENU ====================
                System.out.println("\n┌─────────────────────────────────┐");
                System.out.println("│        REVPLAY - WELCOME        │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  AUTH                           │");
                System.out.println("│    1. Register as User          │");
                System.out.println("│    2. Register as Artist        │");
                System.out.println("│    3. Login                     │");
                System.out.println("│    4. Forgot Password           │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  BROWSE                         │");
                System.out.println("│    5. View All Songs            │");
                System.out.println("│    6. Search Songs              │");
                System.out.println("│    7. Browse Songs by Genre     │");
                System.out.println("│    8. View Public Playlists     │");
                System.out.println("│    9. View All Podcasts         │");
                System.out.println("│   10. Search Podcasts           │");
                System.out.println("│   11. Browse Podcasts by Genre  │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│    0. Exit                      │");
                System.out.println("└─────────────────────────────────┘");
                System.out.print("Choose: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        userController.register();
                        break;
                    case 2:
                        userController.registerArtist();
                        break;
                    case 3:
                        userController.login();
                        break;
                    case 4:
                        userController.forgotPassword();
                        break;
                    case 5:
                        songController.viewSongs();
                        break;
                    case 6:
                        songController.searchSongs();
                        break;
                    case 7:
                        songController.browseByGenre();
                        break;
                    case 8:
                        playlistController.viewPublicPlaylists();
                        break;
                    case 9:
                        podcastController.viewPodcasts();
                        break;
                    case 10:
                        podcastController.searchPodcasts();
                        break;
                    case 11:
                        podcastController.browseByGenre();
                        break;
                    case 0:
                        System.out.println("Thank you for using RevPlay! Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }

            } else if (LoggedInUser.isUser()) {
                // ==================== USER MENU ====================
                System.out.println("\n┌─────────────────────────────────┐");
                System.out.println("│         USER  DASHBOARD         │");
                System.out.println("│         ID: " + String.format("%-10d", LoggedInUser.currentUserId) + "        │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MUSIC                          │");
                System.out.println("│    1. View All Songs            │");
                System.out.println("│    2. Play Song                 │");
                System.out.println("│    3. Pause Song                │");
                System.out.println("│    4. Skip Song                 │");
                System.out.println("│    5. Toggle Repeat             │");
                System.out.println("│    6. Search Songs              │");
                System.out.println("│    7. Browse by Genre           │");
                System.out.println("│    8. View All Genres           │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  FAVORITES                      │");
                System.out.println("│    9. Add to Favorites          │");
                System.out.println("│   10. View My Favorites         │");
                System.out.println("│   11. Remove from Favorites     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  PLAYLISTS                      │");
                System.out.println("│   12. Create Playlist           │");
                System.out.println("│   13. View My Playlists         │");
                System.out.println("│   14. Update Playlist           │");
                System.out.println("│   15. Delete Playlist           │");
                System.out.println("│   16. Add Song to Playlist      │");
                System.out.println("│   17. Remove Song from Playlist │");
                System.out.println("│   18. View Songs in Playlist    │");
                System.out.println("│   19. View Public Playlists     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  PODCASTS                       │");
                System.out.println("│   20. View All Podcasts         │");
                System.out.println("│   21. Play Podcast              │");
                System.out.println("│   22. Search Podcasts           │");
                System.out.println("│   23. Browse Podcasts by Genre  │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  HISTORY                        │");
                System.out.println("│   24. View Listening History    │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  ACCOUNT                        │");
                System.out.println("│   25. Change Password           │");
                System.out.println("│   26. Logout                    │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│    0. Exit                      │");
                System.out.println("└─────────────────────────────────┘");
                System.out.print("Choose: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        songController.viewSongs();
                        break;
                    case 2:
                        songController.playSong();
                        break;
                    case 3:
                        songController.pauseSong();
                        break;
                    case 4:
                        songController.skipSong();
                        break;
                    case 5:
                        songController.toggleRepeat();
                        break;
                    case 6:
                        songController.searchSongs();
                        break;
                    case 7:
                        songController.browseByGenre();
                        break;
                    case 8:
                        songController.viewAllGenres();
                        break;
                    case 9:
                        favoriteController.addFavorite();
                        break;
                    case 10:
                        favoriteController.viewFavorites();
                        break;
                    case 11:
                        favoriteController.removeFavorite();
                        break;
                    case 12:
                        playlistController.createPlaylist();
                        break;
                    case 13:
                        playlistController.viewPlaylists();
                        break;
                    case 14:
                        playlistController.updatePlaylist();
                        break;
                    case 15:
                        playlistController.deletePlaylist();
                        break;
                    case 16:
                        playlistController.addSongToPlaylist();
                        break;
                    case 17:
                        playlistController.removeSongFromPlaylist();
                        break;
                    case 18:
                        playlistController.viewSongsInPlaylist();
                        break;
                    case 19:
                        playlistController.viewPublicPlaylists();
                        break;
                    case 20:
                        podcastController.viewPodcasts();
                        break;
                    case 21:
                        podcastController.playPodcast();
                        break;
                    case 22:
                        podcastController.searchPodcasts();
                        break;
                    case 23:
                        podcastController.browseByGenre();
                        break;
                    case 24:
                        historyController.viewHistory();
                        break;
                    case 25:
                        userController.changePassword();
                        break;
                    case 26:
                        userController.logout();
                        break;
                    case 0:
                        System.out.println("Thank you for using RevPlay! Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }

            } else if (LoggedInUser.isArtist()) {
                // ==================== ARTIST MENU ====================
                System.out.println("\n┌─────────────────────────────────┐");
                System.out.println("│       ARTIST  DASHBOARD         │");
                System.out.println("│         ID: " + String.format("%-10d", LoggedInUser.currentUserId) + "        │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MUSIC                          │");
                System.out.println("│    1. View All Songs            │");
                System.out.println("│    2. Play Song                 │");
                System.out.println("│    3. Pause Song                │");
                System.out.println("│    4. Skip Song                 │");
                System.out.println("│    5. Toggle Repeat             │");
                System.out.println("│    6. Search Songs              │");
                System.out.println("│    7. Browse by Genre           │");
                System.out.println("│    8. View All Genres           │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  FAVORITES                      │");
                System.out.println("│    9. Add to Favorites          │");
                System.out.println("│   10. View My Favorites         │");
                System.out.println("│   11. Remove from Favorites     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  PLAYLISTS                      │");
                System.out.println("│   12. Create Playlist           │");
                System.out.println("│   13. View My Playlists         │");
                System.out.println("│   14. Update Playlist           │");
                System.out.println("│   15. Delete Playlist           │");
                System.out.println("│   16. Add Song to Playlist      │");
                System.out.println("│   17. Remove Song from Playlist │");
                System.out.println("│   18. View Songs in Playlist    │");
                System.out.println("│   19. View Public Playlists     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  PODCASTS                       │");
                System.out.println("│   20. View All Podcasts         │");
                System.out.println("│   21. Play Podcast              │");
                System.out.println("│   22. Search Podcasts           │");
                System.out.println("│   23. Browse Podcasts by Genre  │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  HISTORY                        │");
                System.out.println("│   24. View Listening History    │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MY SONGS (Artist)              │");
                System.out.println("│   25. Add Song                  │");
                System.out.println("│   26. Update Song               │");
                System.out.println("│   27. Delete Song               │");
                System.out.println("│   28. View My Uploaded Songs    │");
                System.out.println("│   29. View My Song Statistics   │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MY PROFILE (Artist)            │");
                System.out.println("│   30. Create Artist Profile     │");
                System.out.println("│   31. View My Artist Profile    │");
                System.out.println("│   32. Update Artist Profile     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MY ALBUMS (Artist)             │");
                System.out.println("│   33. Create Album              │");
                System.out.println("│   34. View My Albums            │");
                System.out.println("│   35. Delete Album              │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  MY PODCASTS (Artist)           │");
                System.out.println("│   36. Add Podcast               │");
                System.out.println("│   37. Update Podcast            │");
                System.out.println("│   38. Delete Podcast            │");
                System.out.println("│   39. View My Podcasts          │");
                System.out.println("│   40. View My Podcast Stats     │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  ANALYTICS (Artist)             │");
                System.out.println("│   41. View Users Who Favorited  │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│  ACCOUNT                        │");
                System.out.println("│   42. Change Password           │");
                System.out.println("│   43. Logout                    │");
                System.out.println("├─────────────────────────────────┤");
                System.out.println("│    0. Exit                      │");
                System.out.println("└─────────────────────────────────┘");
                System.out.print("Choose: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        songController.viewSongs();
                        break;
                    case 2:
                        songController.playSong();
                        break;
                    case 3:
                        songController.pauseSong();
                        break;
                    case 4:
                        songController.skipSong();
                        break;
                    case 5:
                        songController.toggleRepeat();
                        break;
                    case 6:
                        songController.searchSongs();
                        break;
                    case 7:
                        songController.browseByGenre();
                        break;
                    case 8:
                        songController.viewAllGenres();
                        break;
                    case 9:
                        favoriteController.addFavorite();
                        break;
                    case 10:
                        favoriteController.viewFavorites();
                        break;
                    case 11:
                        favoriteController.removeFavorite();
                        break;
                    case 12:
                        playlistController.createPlaylist();
                        break;
                    case 13:
                        playlistController.viewPlaylists();
                        break;
                    case 14:
                        playlistController.updatePlaylist();
                        break;
                    case 15:
                        playlistController.deletePlaylist();
                        break;
                    case 16:
                        playlistController.addSongToPlaylist();
                        break;
                    case 17:
                        playlistController.removeSongFromPlaylist();
                        break;
                    case 18:
                        playlistController.viewSongsInPlaylist();
                        break;
                    case 19:
                        playlistController.viewPublicPlaylists();
                        break;
                    case 20:
                        podcastController.viewPodcasts();
                        break;
                    case 21:
                        podcastController.playPodcast();
                        break;
                    case 22:
                        podcastController.searchPodcasts();
                        break;
                    case 23:
                        podcastController.browseByGenre();
                        break;
                    case 24:
                        historyController.viewHistory();
                        break;
                    case 25:
                        songController.addSong();
                        break;
                    case 26:
                        songController.updateSong();
                        break;
                    case 27:
                        songController.deleteSong();
                        break;
                    case 28:
                        songController.viewMySongs();
                        break;
                    case 29:
                        songController.viewMySongStatistics();
                        break;
                    case 30:
                        artistController.createProfile();
                        break;
                    case 31:
                        artistController.viewProfile();
                        break;
                    case 32:
                        artistController.updateProfile();
                        break;
                    case 33:
                        albumController.createAlbum();
                        break;
                    case 34:
                        albumController.viewMyAlbums();
                        break;
                    case 35:
                        albumController.deleteAlbum();
                        break;
                    case 36:
                        podcastController.addPodcast();
                        break;
                    case 37:
                        podcastController.updatePodcast();
                        break;
                    case 38:
                        podcastController.deletePodcast();
                        break;
                    case 39:
                        podcastController.viewMyPodcasts();
                        break;
                    case 40:
                        podcastController.viewMyPodcastStatistics();
                        break;
                    case 41:
                        favoriteController.viewUsersWhoFavoritedMySongs();
                        break;
                    case 42:
                        userController.changePassword();
                        break;
                    case 43:
                        userController.logout();
                        break;
                    case 0:
                        System.out.println("Thank you for using RevPlay! Bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");
                }
            }
        }
    }
}
