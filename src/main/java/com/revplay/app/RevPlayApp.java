package com.revplay.app;

import java.util.Scanner;

import com.revplay.controller.UserController;
import com.revplay.controller.SongController;
import com.revplay.controller.FavoriteController;
import com.revplay.controller.PlaylistController;
import com.revplay.controller.ListeningHistoryController;
import com.revplay.controller.ArtistProfileController;
import com.revplay.controller.AlbumController;


public class RevPlayApp {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        UserController userController = new UserController();
        SongController songController = new SongController();
        FavoriteController favoriteController = new FavoriteController();
        PlaylistController playlistController = new PlaylistController();
        ListeningHistoryController historyController =
                new ListeningHistoryController();
        ArtistProfileController artistController =
                new ArtistProfileController();
        AlbumController albumController = new AlbumController();


        while (true) {

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Song");
            System.out.println("4. View Songs");
            System.out.println("5. Delete Song");
            System.out.println("6. Play Song");
            System.out.println("7. Exit");
            System.out.println("8. Add Favorite");
            System.out.println("9. View Favorites");
            System.out.println("10. Remove Favorite");
            System.out.println("11. Search Songs");
            System.out.println("12. Create Playlist");
            System.out.println("13. View Playlists");
            System.out.println("14. Delete Playlist");
            System.out.println("15. View Listening History");
            System.out.println("16. Create Artist Profile");
            System.out.println("17. View Artist Profile");
            System.out.println("18. View My Uploaded Songs");
            System.out.println("19. Update Artist Profile");
            System.out.println("20. Create Album");
            System.out.println("21. View My Albums");
            System.out.println("22. Delete Album");
            System.out.println("23. Update Song");
            System.out.println("24. Pause Song");
            System.out.println("25. Skip Song");
            System.out.println("26. Toggle Repeat");
            System.out.println("27. Change Password");





            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    userController.register();
                    break;

                case 2:
                    userController.login();
                    break;

                case 3:
                    songController.addSong();
                    break;

                case 4:
                    songController.viewSongs();
                    break;

                case 5:
                    songController.deleteSong();
                    break;

                case 6:
                    songController.playSong();
                    break;

                case 7:
                    System.out.println("Bye!");
                    System.exit(0);
                    break;

                case 8:
                    favoriteController.addFavorite();
                    break;

                case 9:
                    favoriteController.viewFavorites();
                    break;

                case 10:
                    favoriteController.removeFavorite();
                    break;

                case 11:
                    songController.searchSongs();
                    break;

                case 12:
                    playlistController.createPlaylist();
                    break;

                case 13:
                    playlistController.viewPlaylists();
                    break;

                case 14:
                    playlistController.deletePlaylist();
                    break;

                case 15:
                    historyController.viewHistory();
                    break;

                case 16:
                    artistController.createProfile();
                    break;

                case 17:
                    artistController.viewProfile();
                    break;

                case 18:
                    songController.viewMySongs();
                    break;

                case 19:
                    artistController.updateProfile();
                    break;
                case 20:
                    albumController.createAlbum();
                    break;

                case 21:
                    albumController.viewMyAlbums();
                    break;

                case 22:
                    albumController.deleteAlbum();
                    break;
                case 23:
                    songController.updateSong();
                    break;
                case 24:
                    songController.pauseSong();
                    break;

                case 25:
                    songController.skipSong();
                    break;

                case 26:
                    songController.toggleRepeat();
                    break;
                case 27:
                	userController.changePassword();

                    break;





                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
