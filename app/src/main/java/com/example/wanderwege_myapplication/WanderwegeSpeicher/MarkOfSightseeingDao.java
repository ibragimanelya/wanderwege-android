package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MarkOfSightseeingDao {
    @Query("SELECT * FROM mark_of_sightseeing WHERE id = :markId")
    MarkOfSightseeing getMarkOfSightseeingById(int markId);
    @Insert
    void insert(MarkOfSightseeing markOfSightseeing);
}