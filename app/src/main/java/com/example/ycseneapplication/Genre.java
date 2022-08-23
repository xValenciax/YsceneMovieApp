package com.example.ycseneapplication;

import com.google.gson.annotations.SerializedName;

public class Genre {
    private int id;
    @SerializedName("name")
    private String genre;

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
