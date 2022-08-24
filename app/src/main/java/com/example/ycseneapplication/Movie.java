package com.example.ycseneapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private List<Genre> genres;
    private String poster_path;
    private String release_date;
    @SerializedName("vote_average")
    private String rating;

    public Movie(int id, String title, String overview, List<Genre> genres, String poster_path, String release_date, String rating) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.genres = genres;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.rating = rating;
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

    public List<Genre>  getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
