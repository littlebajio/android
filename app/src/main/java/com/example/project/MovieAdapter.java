package com.example.project;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Holder> {

    private final List<Movie> list;
    private final Context ctx;

    public MovieAdapter(Context c, List<Movie> l) {
        ctx = c;
        list = l;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_movie, p, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int pos) {
        Movie m = list.get(pos);
        h.title.setText(m.getTitle());
        h.img.setImageResource(m.getImageResId());
        h.rate.setRating(m.getRating());

        h.btnFav.setText("Добавить в избранное");

        h.btnFav.setOnClickListener(v -> {
            if (FavoriteManager.get(ctx).stream()
                    .anyMatch(f -> f.getTitle().equals(m.getTitle()))) {
                Toast.makeText(ctx, "Уже в избранном", Toast.LENGTH_SHORT).show();
            } else {
                FavoriteManager.add(ctx, new Favorite(
                        m.getTitle(), m.getImageResId(), m.getRating(), m.getMood()));
                Toast.makeText(ctx, "Добавлено!", Toast.LENGTH_SHORT).show();
            }
        });

        h.rate.setOnRatingBarChangeListener((rb, val, fromUser) -> m.setRating(val));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        Button btnFav;
        RatingBar rate;

        Holder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.movieImage);
            title = v.findViewById(R.id.movieTitle);
            btnFav = v.findViewById(R.id.favoriteButton);
            rate = v.findViewById(R.id.ratingBar);
        }
    }
}
