package com.example.project;

public class Favorite {
    private final String title;
    private final int imageResId;
    private float rating;
    private final String mood;

    public Favorite(String title, int imageResId, float rating, String mood) {
        this.title = title;
        this.imageResId = imageResId;
        this.rating = rating;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getMood() {
        return mood;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Favorite && title.equals(((Favorite) o).title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
