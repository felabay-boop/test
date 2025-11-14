package main;

//pwet

import musicplayer.*;
import storage.AlbumTextReader;
import storage.StorageManager;

import java.nio.file.*;
import java.util.List;
import java.io.IOException;

public class Run1 {
    public static void main(String[] args) {
        MusicPlayer player = new MusicPlayer("barfoo");

        // 1) Load albums from all .txt files inside ./storage
        Path storageDir = Paths.get("storage");
        try {
            if (!Files.exists(storageDir)) Files.createDirectories(storageDir);
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(storageDir, "*.txt")) {
                for (Path p : ds) {
                    try {
                        Album a = AlbumTextReader.read(p);
                        player.addAlbum(a);
                    } catch (IOException e) {
                        System.err.println("Failed to read " + p + ": " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error ensuring storage dir: " + e.getMessage());
        }

        // 2) Print all songs
        player.printAllSongs();

        // 3) Print album list
        player.printAllAlbums();

        // 4) Print a specific album
        Album chosen = player.findAlbum("Last Dance");
        if (chosen != null) {
            MusicPlayer.banner();
            chosen.printTracklist();
            MusicPlayer.banner();
        } else {
            MusicPlayer.banner();
            System.out.println("Album Last Dance does not exist.");
            MusicPlayer.banner();
        }

        // 5) Try to lookup a missing album to mimic sample output
        MusicPlayer.banner();
        Album notExist = player.findAlbum("Glitched Universe");
        if (notExist == null) {
            System.out.println("Album Glitched Universe does not exist.");
        }
        MusicPlayer.banner();

        // 6) Print songs by an artist
        MusicPlayer.banner();
        System.out.println("Songs by Ichika Nito :");
        List<Song> ichika = player.getSongsByArtist("Ichika Nito");
        for (Song s : ichika) System.out.println(" " + s.toString());
        MusicPlayer.banner();

        // 7) Try to find a song title inside an album (case sensitive mismatch to show 'does not exist')
        MusicPlayer.banner();
        if (chosen != null && chosen.findSongByTitle("Reprologue") == null) {
            System.out.println("Song Reprologue does not exist in Last Dance.");
        }
        MusicPlayer.banner();

        // 8) Create playlist and add a few songs if present
        Playlist test = new Playlist("Test list");
        // Attempt to add some known titles if available
        for (Album a : player.getAlbums()) {
            Song s = a.findSongByTitle("Orb");
            if (s != null) test.add(s);
            s = a.findSongByTitle("Metaphor");
            if (s != null) test.add(s);
            s = a.findSongByTitle("Ethereal Feel");
            if (s != null) test.add(s);
            s = a.findSongByTitle("RePrologue");
            if (s != null) test.add(s);
        }
        player.addPlaylist(test);

        // 9) Print playlist list and details
        player.printAllPlaylists();
        test.print();

        // 10) Save albums and playlists via serialization
        System.out.println("Saving.....");
        StorageManager sm = new StorageManager(storageDir);
        try {
            sm.save(player);
            System.out.println("Saving complete.");
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }
}
