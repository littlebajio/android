package com.example.project;

import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class MoodMoviesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.fragment_mood_movies, container, false);

        String mood = requireArguments().getString("mood", "Неизвестное настроение");
        TextView title = v.findViewById(R.id.moodMoviesTitle);
        title.setText("Фильмы для настроения: " + mood);

        RecyclerView rv = v.findViewById(R.id.recyclerViewMoodMovies);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(new MovieAdapter(requireContext(), getMoviesForMood(mood)));

        return v;
    }

    private List<Movie> getMoviesForMood(String mood) {
        Map<String, List<Movie>> moodMovies = new HashMap<>();
        moodMovies.put("Весёлое", Arrays.asList(
                new Movie("Мальчишник в Вегасе", R.drawable.movie4, "Комедия", "Весёлое"),
                new Movie("Ну разве не романтично?", R.drawable.movie7, "Комедия", "Весёлое")
        ));
        moodMovies.put("Грустное", Arrays.asList(
                new Movie("Куда приводят мечты", R.drawable.movie19, "Драма", "Грустное"),
                new Movie("Титаник", R.drawable.movie9, "Драма", "Грустное")
        ));
        moodMovies.put("Острые ощущения", Arrays.asList(
                new Movie("Оно", R.drawable.it, "Приключения", "Острые ощущения"),
                new Movie("Сияние", R.drawable.shining, "Экшн", "Острые ощущения")
        ));
        moodMovies.put("Любопытное", Arrays.asList(
                new Movie("Интерстеллар", R.drawable.interstellar, "Научная фантастика", "Любопытное"),
                new Movie("Начало", R.drawable.inception, "Драма", "Любопытное")
        ));
        moodMovies.put("Гнев", Arrays.asList(
                new Movie("Бойцовский клуб", R.drawable.movie3, "Экшн", "Гнев"),
                new Movie("Крестный отец", R.drawable.movie1, "Драма", "Гнев")
        ));
        moodMovies.put("Расслабляющее", Arrays.asList(
                new Movie("Ла-Ла Ленд", R.drawable.movie10, "Мюзикл", "Расслабляющее"),
                new Movie("Суперсемейка", R.drawable.movie11, "Анимация", "Расслабляющее")
        ));
        moodMovies.put("Мотивирующее", Arrays.asList(
                new Movie("Форест Гамп", R.drawable.movie2, "Спорт", "Мотивирующее"),
                new Movie("Лев", R.drawable.movie12, "Документальный", "Мотивирующее")
        ));

        return moodMovies.getOrDefault(mood, Collections.emptyList());
    }
}
