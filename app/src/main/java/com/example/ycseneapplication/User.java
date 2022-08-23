package com.example.ycseneapplication;

import java.util.List;

public class User {
    private static int idCount = 0;
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String bio;
    private String password;
    private List<Integer> favorite_movies;

    public User(){}

    public User(String name, String email, String phone_number, String password) {
        idCount++;
        this.id = idCount;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return bio;
    }

    public void setDescription(String description) {
        this.bio = description;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Integer> getFavorite_movies() {
        return favorite_movies;
    }

    public void setFavorite_movies(List<Integer> favorite_movies) {
        this.favorite_movies = favorite_movies;
    }
}
