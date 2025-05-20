package com.example.project;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inf.inflate(R.layout.fragment_settings, container, false);

        setupMoodButtons(v);

        return v;
    }

    private void setupMoodButtons(View v) {
        v.findViewById(R.id.mood_happy).setOnClickListener(view -> openMoodMovies("Весёлое"));
        v.findViewById(R.id.mood_sad).setOnClickListener(view -> openMoodMovies("Грустное"));
        v.findViewById(R.id.mood_thrill).setOnClickListener(view -> openMoodMovies("Острые ощущения"));
        v.findViewById(R.id.mood_curious).setOnClickListener(view -> openMoodMovies("Любопытное"));
        v.findViewById(R.id.mood_angry).setOnClickListener(view -> openMoodMovies("Гнев"));
        v.findViewById(R.id.mood_relax).setOnClickListener(view -> openMoodMovies("Расслабляющее"));
        v.findViewById(R.id.mood_motivating).setOnClickListener(view -> openMoodMovies("Мотивирующее"));
    }

    private void openMoodMovies(String mood) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MoodMoviesFragment moodMoviesFragment = new MoodMoviesFragment();

        Bundle args = new Bundle();
        args.putString("mood", mood);
        moodMoviesFragment.setArguments(args);

        fragmentTransaction.replace(R.id.fragment_container, moodMoviesFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
