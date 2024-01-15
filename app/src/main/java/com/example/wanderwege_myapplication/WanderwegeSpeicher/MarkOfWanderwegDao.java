package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MarkOfWanderwegDao {
    @Query("SELECT * FROM mark_of_wanderweg")
    List<MarkOfWanderweg> getAllWanderwegsMarks();
    @Insert
    void insert(MarkOfWanderweg markOfWanderweg);
}

