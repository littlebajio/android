package com.example.project;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class HomeFragment extends Fragment {

    private MovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup c,
                             @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_home, c, false);
        RecyclerView rv = v.findViewById(R.id.recyclerView);

        List<Movie> data = makeMovies();
        adapter = new MovieAdapter(requireContext(), data);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        return v;
    }

    private List<Movie> makeMovies() {
        List<Movie> m = new ArrayList<>();
        m.add(new Movie("Inception", R.drawable.inception,
                "Сны и архитекторы сознания", "mind"));
        m.add(new Movie("Interstellar", R.drawable.interstellar,
                "Космос и любовь", "space"));
        m.add(new Movie("The Godfather", R.drawable.movie1,
                "Гангстерская сага о семье Корлеоне", "space"));
        m.add(new Movie("Forrest Gump", R.drawable.movie2,
                "Простой парень, случайно меняющий историю США", "space"));
        m.add(new Movie("Fight club", R.drawable.movie3,
                "Антипотребительский манифест", "space"));
        m.add(new Movie("The Hangover", R.drawable.movie4,
                "Пьяный угар в Лас-Вегасе", "space"));
        return m;
    }
}
