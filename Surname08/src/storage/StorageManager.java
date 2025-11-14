package storage;

import musicplayer.MusicPlayer;
import musicplayer.Album;
import musicplayer.Playlist;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles serialization/deserialization of albums and playlists
 * to two separate files inside the ./storage directory.
 */
public class StorageManager {

    private final Path storageDir;
    private final Path albumsSer;
    private final Path playlistsSer;

    public StorageManager(Path storageDir) {
        this.storageDir = storageDir;
        this.albumsSer = storageDir.resolve("albums.dat");
        this.playlistsSer = storageDir.resolve("playlists.dat");
    }

    public void ensureDir() throws IOException {
        if (!Files.exists(storageDir)) Files.createDirectories(storageDir);
    }

    public void save(MusicPlayer player) throws IOException {
        ensureDir();
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(albumsSer)))) {
            oos.writeObject(new ArrayList<>(player.getAlbums()));
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(playlistsSer)))) {
            oos.writeObject(new ArrayList<>(player.getPlaylists()));
        }
    }

    @SuppressWarnings("unchecked")
    public void loadInto(MusicPlayer player) throws IOException, ClassNotFoundException {
        ensureDir();
        if (Files.exists(albumsSer)) {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(albumsSer)))) {
                List<Album> albums = (List<Album>) ois.readObject();
                for (Album a : albums) player.addAlbum(a);
            }
        }
        if (Files.exists(playlistsSer)) {
            try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(playlistsSer)))) {
                List<Playlist> pls = (List<Playlist>) ois.readObject();
                for (Playlist p : pls) player.addPlaylist(p);
            }
        }
    }
}
