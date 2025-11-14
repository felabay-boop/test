package musicplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a playlist (name + ordered list of songs).
 */
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;
    private final List<Song> tracks = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Song> getTracks() { return Collections.unmodifiableList(tracks); }

    public void add(Song s) { if (s != null) tracks.add(s); }
    public void remove(Song s) { tracks.remove(s); }

    public void print() {
        System.out.println("Playlist name: " + name);
        System.out.println("Tracks:");
        int i = 1;
        for (Song s : tracks) {
            System.out.printf("%d  | %s%n", i, s.toString());
            i++;
        }
    }
}
