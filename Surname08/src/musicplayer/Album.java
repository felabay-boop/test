package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an album with metadata and list of songs.
 */
public class Album implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final String artist;
    private final int releaseYear;
    private final List<Song> songs = new ArrayList<>();

    public Album(String name, String artist, int releaseYear) {
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public String getName() { return name; }
    public String getArtist() { return artist; }
    public int getReleaseYear() { return releaseYear; }
    public List<Song> getSongs() { return Collections.unmodifiableList(songs); }

    public void addSong(Song song) {
        if (song != null) songs.add(song);
    }

    public int getTotalDurationSeconds() {
        int sum = 0;
        for (Song s : songs) sum += s.getDurationSeconds();
        return sum;
    }

    public Song findSongByTitle(String title) {
        if (title == null) return null;
        for (Song s : songs) {
            if (s.getTitle().equalsIgnoreCase(title.trim())) return s;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d)", name, artist, releaseYear);
    }

    public void printTracklist() {
        System.out.println(this.toString());
        int i = 1;
        for (Song s : songs) {
            System.out.printf(" %d  |%s%n", i, s.toString());
            i++;
        }
    }
}
