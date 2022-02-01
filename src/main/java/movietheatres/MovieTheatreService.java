package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    public static final String SEPARATOR_CINEMA_FILM = "-";
    public static final String SEPARATOR_FILM_TIME = ";";

    private List<Movie> movies = new ArrayList<>();
    private Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public void readFromFile(Path path) {
        String line;

        try {
            BufferedReader reader = Files.newBufferedReader(path);
            while ((line = reader.readLine()) != null) {
                String key = parseLineCinema(line);
                String title = parseLineTitle(line);
                LocalTime time = parseLineTime(line);

                shows.putIfAbsent(key, new ArrayList<>());
                movies = shows.get(key);
                movies.add(new Movie(title, time));
                movies.sort(Comparator.comparing(Movie::getStartTime));

            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error by parsing, general io", ioe);
        }
    }

    private String parseLineCinema(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);
        String cinema = fullLine[0];

        return cinema;
    }

    private String parseLineTitle(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);

        String[] titleAndTime = fullLine[1].split(SEPARATOR_FILM_TIME);
        String title = titleAndTime[0];

        return title;
    }

    private LocalTime parseLineTime(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);

        String[] titleAndTime = fullLine[1].split(SEPARATOR_FILM_TIME);
        LocalTime time = LocalTime.parse(titleAndTime[1]);

        return time;
    }

}