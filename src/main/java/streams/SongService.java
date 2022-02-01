package streams;

import java.time.LocalDate;
import java.util.*;

public class SongService {

    private List<Song> songs = new ArrayList<>();


    public void addSong(Song song) {
        if (song != null) {
            songs.add(song);
        }
    }

    public Optional<Song> shortestSong() {
        return songs.stream()
                .min(Comparator.comparingInt(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        return songs.stream()
                .filter(song -> song.getTitle().equals(title))
                .toList();

    }

    public boolean isPerformerInSong(Song song, String performer) {
        return songs.stream()
                .anyMatch(melody-> song.getPerformers().contains(performer));

    }

    public List<String> titlesBeforeDate(LocalDate date) {
        return songs.stream()
                .filter(song -> song.getRelease().isBefore(date))
                .map(Song::getTitle)
                .toList();
    }
}