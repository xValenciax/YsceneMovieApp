package com.example.ycseneapplication;

public class SearchHistory {
    private static int idCount = 0;
    private int id;
    private int movieId;
    private String searchQuery;

    public SearchHistory(int movieId, String searchQuery) {
        idCount++;
        this.id = idCount;
        this.movieId = movieId;
        this.searchQuery = searchQuery;
    }

    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int idCount) {
        SearchHistory.idCount = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
