package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WanderwegDao {
    @Query("SELECT * FROM Wanderweg WHERE id = :wanderwegId")
    Wanderweg getWanderwegById(int wanderwegId);
    @Query("SELECT * FROM wanderweg")
    List<Wanderweg> getAllWanderwegs();
    @Query("SELECT sightseeingIds FROM wanderweg  WHERE id = :wanderwegId")
    String getSSingIdsByWanderwegId(int wanderwegId);
    @Insert
    void insertAll(List<Wanderweg> wanderwegList);
}
