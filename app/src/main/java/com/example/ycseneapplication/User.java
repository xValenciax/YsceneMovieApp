package com.example.ycseneapplication;

public class User {
    private static int idCount = 0;
    private int id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String bio;
    private String password;

    public User(){}
    public User(String first_name, String last_name, String phone_number, String bio, String password) {
        idCount++;
        this.id = idCount;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.bio = bio;
        this.password = password;
    }
    public User(String first_name, String last_name, String phone_number, String password) {
        idCount++;
        this.id = idCount;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
