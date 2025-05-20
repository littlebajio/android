package com.example.project;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static final List<Movie> MOVIES = new ArrayList<>();

    static {
        MOVIES.add(new Movie(
                "Inception",
                R.drawable.inception,
                "Кристофер Нолан погружает нас в мир снов, где возможно всё.",
                "Хочется острых ощущений"));

        MOVIES.add(new Movie(
                "Interstellar",
                R.drawable.interstellar,
                "Эпическое путешествие сквозь галактики о силе любви и времени.",
                "Грустное"));
    }

    public static List<Movie> getAll() { return MOVIES; }

    public static Movie random() {
        return MOVIES.get((int) (Math.random() * MOVIES.size()));
    }

    public static List<Movie> byMood(String mood) {
        List<Movie> res = new ArrayList<>();
        for (Movie m : MOVIES) if (m.getMood().equals(mood)) res.add(m);
        return res;
    }

    public static List<Movie> search(String q){
        List<Movie> res = new ArrayList<>();
        String low = q.toLowerCase();
        for (Movie m:MOVIES)
            if (m.getTitle().toLowerCase().contains(low) ||
                    m.getDescription().toLowerCase().contains(low))
                res.add(m);
        return res;
    }

    public static List<String> getMoods() {
        List<String> moods = new ArrayList<>();
        for (Movie m : MOVIES)
            if (!moods.contains(m.getMood())) moods.add(m.getMood());
        return moods;
    }
}
