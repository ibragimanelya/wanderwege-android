package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "mark_of_wanderweg")
public class MarkOfWanderweg {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double longitude;
    private double latitude;

    public MarkOfWanderweg() {
    }

    // Constructor, getters, and setters
    @Ignore
    public MarkOfWanderweg(double longitude, double latitude) {
        setLongitude(longitude);
        setLatitude(latitude);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
