package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;
import java.util.List;

public class ProfileFragment extends Fragment {

    private TextView tvUser, tvStats, tvAch;
    private FavoriteAdapter adapter;
    private List<Favorite> favs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inf,
                             @Nullable ViewGroup container,
                             @Nullable Bundle s) {
        View v = inf.inflate(R.layout.fragment_profile, container, false);

        try {
            tvUser = v.findViewById(R.id.tvUsername);
            tvStats = v.findViewById(R.id.tvStats);
            tvAch = v.findViewById(R.id.tvAchievements);
            RecyclerView rv = v.findViewById(R.id.recyclerFavorites);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));

            favs = FavoriteManager.get(requireContext());
            if (favs == null) favs = List.of();

            adapter = new FavoriteAdapter(requireContext(), favs, this::updateStats);
            rv.setAdapter(adapter);

            refreshHeader();
            updateStats();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ProfileFragment", "Ошибка при загрузке данных: " + e.getMessage());
        }

        return v;
    }


    private void refreshHeader() {
        tvUser.setText("Пользователь: " + UserSession.getUsername(requireContext()));
    }

    private void updateStats() {
        float avg = 0;
        for (Favorite f : favs) avg += f.getRating();
        if (!favs.isEmpty()) avg /= favs.size();
        tvStats.setText("В избранном: " + favs.size() + "   Средняя оценка: " + String.format("%.1f", avg));

        String ach = "Достижения: ";
        if (favs.size() >= 1) ach += "Новичок 🎬  ";
        if (favs.size() >= 5) ach += "Киноман 🍿  ";
        if (favs.size() >= 10) ach += "Критик 🏆";
        tvAch.setText(ach);
    }
}
