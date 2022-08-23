package com.example.ycseneapplication;

import com.google.gson.annotations.SerializedName;

public class Movie {
    private int id;
    private String title;
    private String original_title;
    private String overview;
    private Genre genre;
    private String poster_path;
    private String release_date;
    @SerializedName("vote_average")
    private float rating;
    private String video_key;

    public Movie(int id, String title, String overview, Genre genre, String poster_path, String release_date, float rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genre = genre;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.rating = rating;
    }

    public Movie(int id, String title, String overview, Genre genre, String poster_path, String release_date, float rating, String video_key) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genre = genre;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.rating = rating;
        this.video_key = video_key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getVideo_key() {
        return video_key;
    }

    public void setVideo_key(String video_key) {
        this.video_key = video_key;
    }
}
