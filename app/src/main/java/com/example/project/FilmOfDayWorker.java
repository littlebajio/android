package com.example.project;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.*;
import java.util.List;
import java.util.Random;

public class FilmOfDayWorker extends Worker {

    public FilmOfDayWorker(@NonNull Context c, @NonNull WorkerParameters p) {
        super(c, p);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<Movie> movies = List.of(
                new Movie("Интерстеллар", R.drawable.interstellar, "Научная фантастика", "Любопытное"),
                new Movie("Куда приводят мечты", R.drawable.movie19, "Драма", "Грустное"),
                new Movie("Бойцовский клуб", R.drawable.movie3, "Экшн", "Гнев"),
                new Movie("Форест Гамп", R.drawable.movie2, "Спорт", "Мотивирующее"),
                new Movie("Оно", R.drawable.it, "Приключения", "Острые ощущения"),
                new Movie("Ну разве не романтично?", R.drawable.movie7, "Комедия", "Весёлое")
        );

        Random random = new Random();
        Movie randomMovie = movies.get(random.nextInt(movies.size()));

        NotificationHelper.push(getApplicationContext(), randomMovie.getTitle());
        return Result.success();
    }
}
