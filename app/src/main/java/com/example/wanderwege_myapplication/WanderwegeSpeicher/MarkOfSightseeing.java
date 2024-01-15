package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "mark_of_sightseeing")

public class MarkOfSightseeing {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double longitude;
    private double latitude;

    public MarkOfSightseeing(){}
    // Constructor, getters, and setters
    @Ignore
    public MarkOfSightseeing(double latitude, double longitude) {
        setLongitude(longitude);
        setLatitude(latitude);
    }

    public int getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}