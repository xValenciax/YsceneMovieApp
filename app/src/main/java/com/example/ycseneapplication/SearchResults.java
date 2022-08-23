package com.example.ycseneapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {
    List<Movie> results;
    int total_pages;

    public SearchResults(List<Movie> results) {
        this.results = results;
    }

    public List<Movie> getMovies() {
        return results;
    }

    public void setMovies(List<Movie> results) {
        this.results = results;
    }
}
