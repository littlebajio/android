package com.example.project;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {

    private final List<Favorite> list;
    private final Context ctx;
    private final Runnable onChange;

    public FavoriteAdapter(Context ctx, List<Favorite> list, Runnable onChange) {
        this.ctx = ctx;
        this.list = list;
        this.onChange = onChange;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Favorite fav = list.get(position);

        holder.title.setText(fav.getTitle());
        holder.img.setImageResource(fav.getImageResId());
        holder.rate.setRating(fav.getRating());

        holder.del.setOnClickListener(v -> {
            FavoriteManager.remove(ctx, fav);

            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                list.remove(pos);
                notifyItemRemoved(pos);
                onChange.run();
            }

            Snackbar.make(holder.itemView, "Удалено", Snackbar.LENGTH_LONG)
                    .setAction("Undo", v1 -> {
                        FavoriteManager.add(ctx, fav);
                        list.add(pos, fav);
                        notifyItemInserted(pos);
                        onChange.run();
                    }).show();
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
    static class Holder extends RecyclerView.ViewHolder {
        final ImageView img;
        final TextView title;
        final RatingBar rate;
        final ImageButton del;

        Holder(@NonNull View v) {
            super(v);
            img = v.findViewById(R.id.favoriteImage);
            title = v.findViewById(R.id.favoriteTitle);
            rate = v.findViewById(R.id.favoriteRatingBar);
            del = v.findViewById(R.id.btnDeleteFavorite);
            rate.setIsIndicator(true);
        }
    }
}
