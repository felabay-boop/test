package storage;

import musicplayer.Album;
import musicplayer.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a plaintext album file into an Album object.
 *
 * Supported flexible formats (any of these will work):
 *
 * A) Keyed header + 'Songs:' section (recommended)
 *    Album: Last Dance
 *    Artist: Sakuzyo
 *    Year: 2015
 *    Songs:
 *    1|RePrologue|Sakuzyo|3:23
 *    2|First Dance|Sakuzyo|3:13
 *
 * B) CSV-like (no header; first line album, artist, year)
 *    Last Dance,Sakuzyo,2015
 *    1,RePrologue,Sakuzyo,3:23
 *    2,First Dance,Sakuzyo,3:13
 *
 * C) Looser format using " - " and " | "
 *    Album: Last Dance - Artist: Sakuzyo - Year: 2015
 *    1 | RePrologue | Sakuzyo | 3:23
 */
public class AlbumTextReader {

    public static Album read(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        String albumName = null;
        String artist = null;
        int year = 0;
        List<String> songLines = new ArrayList<>();

        boolean inSongs = false;
        for (String raw : lines) {
            String line = raw.trim();
            if (line.isEmpty()) continue;

            // Detect header keys
            if (line.toLowerCase().startsWith("album:")) {
                albumName = line.substring(line.indexOf(':') + 1).trim();
                continue;
            }
            if (line.toLowerCase().startsWith("artist:")) {
                artist = line.substring(line.indexOf(':') + 1).trim();
                continue;
            }
            if (line.toLowerCase().startsWith("year:")) {
                String y = line.substring(line.indexOf(':') + 1).trim();
                try { year = Integer.parseInt(y); } catch (NumberFormatException ignored) {}
                continue;
            }
            if (line.toLowerCase().startsWith("songs:")) {
                inSongs = true;
                continue;
            }

            if (inSongs) {
                songLines.add(line);
            } else {
                // Maybe it's CSV header: name,artist,year
                if (albumName == null && artist == null && year == 0 && line.contains(",")) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        albumName = parts[0].trim();
                        artist = parts[1].trim();
                        try { year = Integer.parseInt(parts[2].trim()); } catch (NumberFormatException ignored) {}
                    }
                } else {
                    // Or a combined single header line
                    if ((albumName == null || artist == null || year == 0) && line.toLowerCase().contains("album:")) {
                        // attempt to parse: Album: X - Artist: Y - Year: Z
                        String[] tokens = line.split("-");
                        for (String t : tokens) {
                            String lt = t.toLowerCase().trim();
                            if (lt.startsWith("album:")) albumName = t.substring(t.indexOf(':') + 1).trim();
                            if (lt.startsWith("artist:")) artist = t.substring(t.indexOf(':') + 1).trim();
                            if (lt.startsWith("year:")) {
                                String y = t.substring(t.indexOf(':') + 1).trim();
                                try { year = Integer.parseInt(y); } catch (NumberFormatException ignored) {}
                            }
                        }
                    } else {
                        // otherwise treat it as a song row already
                        songLines.add(line);
                    }
                }
            }
        }

        if (albumName == null) albumName = "Unknown Album";
        if (artist == null) artist = "Unknown Artist";

        Album album = new Album(albumName, artist, year);

        for (String sline : songLines) {
            // Try pipe-delimited first: idx|title|artist|mm:ss
            String[] parts;
            if (sline.contains("|")) {
                parts = sline.split("\\|");
            } else if (sline.contains(",")) {
                parts = sline.split(",");
            } else {
                // try space-hyphen format: "1  Title  -  Artist  | 3:23"
                String cleaned = sline.replaceAll("\\s{2,}", " ").trim();
                cleaned = cleaned.replace(" - ", "|").replace(" | ", "|").replace("  |  ", "|");
                parts = cleaned.split("\\|");
            }
            if (parts.length < 4) continue;
            try {
                int idx = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String art = parts[2].trim();
                int dur = Song.parseDurationToSeconds(parts[3].trim());
                album.addSong(new Song(idx, title, art, dur));
            } catch (NumberFormatException ignored) {
                // skip malformed row
            }
        }

        return album;
    }
}
