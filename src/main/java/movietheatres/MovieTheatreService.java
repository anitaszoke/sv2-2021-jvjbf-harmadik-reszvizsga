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

    private final Map<String, List<Movie>> shows = new LinkedHashMap<>();

    public Map<String, List<Movie>> getShows() {
        return shows;
    }

    public void readFromFile(Path path) {
        String line;

        try {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                while ((line = reader.readLine()) != null) {
                    addShow(line);
                }
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Error by parsing, general io", ioe);
        }
    }

    private String parseLineCinema(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);
        return fullLine[0];
    }

    private String parseLineTitle(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);
        String[] titleAndTime = fullLine[1].split(SEPARATOR_FILM_TIME);

        return titleAndTime[0];
    }

    private LocalTime parseLineTime(String line) {
        String[] fullLine = line.split(SEPARATOR_CINEMA_FILM);
        String[] titleAndTime = fullLine[1].split(SEPARATOR_FILM_TIME);

        return LocalTime.parse(titleAndTime[1]);
    }

    private void addShow(String line) {
        String key = parseLineCinema(line);
        String title = parseLineTitle(line);
        LocalTime time = parseLineTime(line);

        shows.putIfAbsent(key, new ArrayList<>());
        List<Movie> movies = shows.get(key);
        movies.add(new Movie(title, time));
        movies.sort(Comparator.comparing(Movie::getStartTime));
    }


    public List<String> findMovie(String title) {
//        Set<String> findTitle = new LinkedHashSet<>();
//        for (Map.Entry<String, List<Movie>> entry : shows.entrySet()) {
//            for (Movie m : entry.getValue()) {
//                if (m.getTitle().equals(title)) {
//                    findTitle.add(entry.getKey());
//                }
//            }
//        }
//        return new ArrayList<>(findTitle);


//        return shows.entrySet().stream()
//                .filter(entry -> entry.getValue().stream()
//                        .anyMatch(movie -> movie.getTitle().equals(title)))
//                .map(stringListEntry -> stringListEntry.getKey())
//                .toList();

        return shows.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .anyMatch(movie -> movie.getTitle().equals(title)))
                .map(Map.Entry::getKey)
                .toList();

    }

    public LocalTime findLatestShow(String title) {
//        LocalTime latestShow = LocalTime.of(0,1);
//        for (Map.Entry<String, List<Movie>> entry : shows.entrySet()) {
//            for (Movie m : entry.getValue()) {
//                if (m.getTitle().equals(title) && m.getStartTime().isAfter(latestShow)) {
//
//                    latestShow = m.getStartTime();
//                }
//            }
//        }
//        if (latestShow.equals(LocalTime.of(0,1))) {
//            throw new IllegalArgumentException("Title not found!");
//        }
//        return latestShow;



//        return shows.entrySet().stream()
//                .flatMap(e -> e.getValue().stream())
//                .filter(movie -> movie.getTitle().equals(title))
//                .max(Comparator.comparing(movie1 -> movie1.getStartTime()))
//                .orElseThrow(() -> new IllegalArgumentException("Not found movie"))
//                .getStartTime();

        return shows.values().stream()
                .flatMap(List::stream)
                .filter(movie -> movie.getTitle().equals(title))
                .max(Comparator.comparing(Movie::getStartTime))
                .orElseThrow(() -> new IllegalArgumentException("Not found movie"))
                .getStartTime();


    }
}


