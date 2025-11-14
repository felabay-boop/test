package main;

import musicplayer.MusicPlayer;
import storage.StorageManager;

import java.nio.file.Paths;

public class Run2 {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer("barfoo");
        StorageManager sm = new StorageManager(Paths.get("storage"));

        System.out.println("Loading.....");
        try {
            sm.loadInto(player);
            System.out.println("Loading complete.");
        } catch (Exception e) {
            System.out.println("Load failed: " + e.getMessage());
        }

        player.printAllSongs();
        player.printAllAlbums();
        player.printAllPlaylists();
    }
}
