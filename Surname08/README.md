# Surname08 — Exercise 08: Persistence (CMSC 22)

This project follows the spec in *Exercise 08: Persistence — Music Player Storage*.

## Packages
- `main`: `Run1`, `Run2`
- `musicplayer`: core domain classes (`Song`, `Album`, `Playlist`, `MusicPlayer`)
- `storage`: `AlbumTextReader` (reads plaintext album files) and `StorageManager` (serialization)

## How to compile and run (CLI)
```bash
cd Surname08/src
# compile
javac main/*.java musicplayer/*.java storage/*.java
# run first pass (loads text files, prints, creates playlist, then saves)
java main.Run1
# run second pass (loads from serialized files)
java main.Run2
```

> The program reads album **plaintext files** from a `./storage` directory (next to where you run it).
> Provide the album `.txt` files there.

## Accepted plaintext album formats
**A. Keyed format (recommended)**
```
Album: Last Dance
Artist: Sakuzyo
Year: 2015
Songs:
1|RePrologue|Sakuzyo|3:23
2|First Dance|Sakuzyo|3:13
...
```

**B. CSV-like (first line is album header)**
```
Last Dance,Sakuzyo,2015
1,RePrologue,Sakuzyo,3:23
2,First Dance,Sakuzyo,3:13
...
```

**C. Loose line format** (we try to recover it)
```
Album: Last Dance - Artist: Sakuzyo - Year: 2015
1 | RePrologue | Sakuzyo | 3:23
2 | First Dance | Sakuzyo | 3:13
...
```

## Notes
- Serialization stores `albums.dat` and `playlists.dat` in the same `./storage` directory.
- `Run1` mimics the sample output flow (print all songs, albums, lookups, playlists, save).
- `Run2` loads the serialized data and prints again.
- Duration arithmetic uses seconds internally; formatter prints `m:ss`.
- The code is defensive and will **skip malformed song rows** instead of crashing.
