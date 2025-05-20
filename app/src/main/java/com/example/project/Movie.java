package com.example.project;

public class Movie {
    private final String title;
    private final int    imageResId;
    private final String description;
    private final String mood;
    private float        rating;

    public Movie(String title, int imageResId,
                 String description, String mood){
        this.title       = title;
        this.imageResId  = imageResId;
        this.description = description;
        this.mood        = mood;
        this.rating      = 0f;
    }

    public String getTitle()       { return title; }
    public int    getImageResId()  { return imageResId; }
    public String getDescription() { return description; }
    public String getMood()        { return mood; }
    public float  getRating()      { return rating; }
    public void   setRating(float r){ rating = r; }
}
