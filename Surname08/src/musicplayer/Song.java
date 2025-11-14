package musicplayer;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents a song/track.
 * Duration is stored as seconds to simplify arithmetic. Use formatDuration() for mm:ss.
 */
public class Song implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int trackNumber;
    private final String title;
    private final String artist;
    private final int durationSeconds;

    public Song(int trackNumber, String title, String artist, int durationSeconds) {
        this.trackNumber = trackNumber;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
    }

    public int getTrackNumber() { return trackNumber; }
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSeconds() { return durationSeconds; }

    public static int parseDurationToSeconds(String mmss) {
        String[] parts = mmss.trim().split(":");
        if (parts.length != 2) return 0;
        try {
            int m = Integer.parseInt(parts[0].trim());
            int s = Integer.parseInt(parts[1].trim());
            return Math.max(0, m * 60 + s);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String formatDuration(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        return String.format("%d:%02d", m, s);
    }

    @Override
    public String toString() {
        return String.format("%-35s - %-28s | %s",
                title, artist, formatDuration(durationSeconds));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;
        Song song = (Song) o;
        return trackNumber == song.trackNumber &&
                durationSeconds == song.durationSeconds &&
                Objects.equals(title, song.title) &&
                Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trackNumber, title, artist, durationSeconds);
    }
}
