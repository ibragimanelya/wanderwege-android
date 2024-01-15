package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RatingDao {
    @Insert
    void insertRating(Rating rating);
    @Query("SELECT * FROM rating WHERE wanderwegId = :wanderwegId")
    List<Rating> getRatingsForWanderweg(int wanderwegId);
    @Query("UPDATE wanderweg SET averageRating = :averageRating WHERE id = :wanderwegId")
    void updateAverageRating(int wanderwegId, double averageRating);
}
