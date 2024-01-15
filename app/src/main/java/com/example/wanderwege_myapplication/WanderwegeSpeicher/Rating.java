package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "rating")
public class Rating {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int rating;
    private int wanderwegId;

    public Rating(){}

    @Ignore
    public Rating(String name, int rating) {
        setName(name);
        setRating(rating);
    }

    public int getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWanderwegId(int wanderwegId) {
        this.wanderwegId = wanderwegId;
    }

    public int getWanderwegId() {
        return wanderwegId;
    }
}