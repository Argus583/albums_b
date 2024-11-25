package com.example.albums_b;


import androidx.annotation.NonNull;

public class Album {
    int id;
    int userId;
    String title;

    public Album(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    @NonNull
    @Override
    public String toString() {
        return "ALBUM " + id + " { " +
                "\nUser ID: " + userId +
                "\nTitle: " + title + "\n}";
    }
}
