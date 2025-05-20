package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class FavoriteManager {

    private static final String PREF = "favorite_pref";
    private static final String KEY = "favorites";
    private static final Gson GSON = new Gson();

    private FavoriteManager() {}

    public static List<Favorite> get(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String json = sp.getString(KEY, null);
        if (json == null) return new ArrayList<>();
        try {
            Type type = new TypeToken<List<Favorite>>() {}.getType();
            return GSON.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void add(Context context, Favorite favorite) {
        List<Favorite> favorites = get(context);
        favorites.add(favorite);
        save(context, favorites);
    }

    public static void remove(Context context, Favorite favorite) {
        List<Favorite> favorites = get(context);
        favorites.remove(favorite);
        save(context, favorites);
    }

    private static void save(Context context, List<Favorite> favorites) {
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(KEY, GSON.toJson(favorites)).apply();
    }
}
