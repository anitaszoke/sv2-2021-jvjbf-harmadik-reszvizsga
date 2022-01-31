package movietheatres;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    public static final String SEPARATOR_CINEMA_FILM = "-";
    public static final String SEPARATOR_FILM_TIME = ";";

    private Map<String, String> shows = new Map<>();

    public Map<String, String> getShows() {
        return shows;
    }

    public Map<String, String> readFromFile(Path path) {
        String line;
        List<String> strings = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
            while ((line = reader.readLine()) != null) {
                strings.add(line);

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error by parsing, general io", ioe);
        }
        return createShows(strings);
    }

    public List<String> findMovie(String title) {
        List<String> findTitle = new ArrayList<>();
        for (Map.Entry<String, String> entry : shows.entrySet()) {
            if (entry.getValue().equals(title)) {
                findTitle.add(entry.getKey());
            }
        }
        return findTitle;
    }

    public LocalTime findLatestShow(String title) {
        LocalTime latestShow;
        for (Map.Entry<String, String> entry : shows.entrySet()) {
            if (entry.getValue().equals(title)) {
                latestShow = entry.getValue().
            } else {
                throw new IllegalArgumentException();
            }
        }
        return latestShow;
    }


    private Map<String, String> createShows(List<String> strings) {
        String key = "";
        String value = "";
        for (String s : strings) {
            String[] temp = s.split(SEPARATOR_CINEMA_FILM);
            key = temp[0];
            value = temp[1];

            shows.put(key, value);
        }
        return shows;
    }
}
