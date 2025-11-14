package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MusicPlayer implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final List<Album> albums = new ArrayList<>();
    private final List<Playlist> playlists = new ArrayList<>();

    public MusicPlayer(String name) { this.name = name; }

    public String getName() { return name; }
    public List<Album> getAlbums() { return Collections.unmodifiableList(albums); }
    public List<Playlist> getPlaylists() { return Collections.unmodifiableList(playlists); }

    public void addAlbum(Album a) { if (a != null) albums.add(a); }
    public void addPlaylist(Playlist p) { if (p != null) playlists.add(p); }

    public Album findAlbum(String name) {
        if (name == null) return null;
        for (Album a : albums) {
            if (a.getName().equalsIgnoreCase(name.trim())) return a;
        }
        return null;
    }

    public List<Song> getAllSongsSortedByTitleThenArtist() {
        List<Song> all = new ArrayList<>();
        for (Album a : albums) all.addAll(a.getSongs());
        all.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Song::getArtist, String.CASE_INSENSITIVE_ORDER));
        return all;
    }

    public List<Song> getSongsByArtist(String artist) {
        List<Song> result = new ArrayList<>();
        if (artist == null) return result;
        for (Album a : albums) {
            for (Song s : a.getSongs()) {
                if (artist.equalsIgnoreCase(s.getArtist())) result.add(s);
            }
        }
        result.sort(Comparator.comparing(Song::getTitle, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    public void printAllSongs() {
        banner();
        System.out.println("All songs currently in player " + name + ":");
        for (Song s : getAllSongsSortedByTitleThenArtist()) {
            System.out.println(" " + s.toString());
        }
        banner();
    }

    public void printAllAlbums() {
        banner();
        System.out.println("All albums currently in player " + name + ":");
        int i = 1;
        for (Album a : albums) {
            System.out.printf("%d %s%n", i, a.getName());
            i++;
        }
        banner();
    }

    public void printAllPlaylists() {
        banner();
        System.out.println("All playlists currently in player " + name + ":");
        int i = 1;
        for (Playlist p : playlists) {
            System.out.printf("%d %s%n", i, p.getName());
            i++;
        }
        banner();
    }

    public static void banner() {
        System.out.println("~||==================================================================================||~");
    }
}
