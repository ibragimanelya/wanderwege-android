package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SightseeingDao {
    @Query("SELECT name FROM sightseeing WHERE id IN (:sightseeingIds)")
    List<String> getSightseeingListOfNamesByIds(List<Integer> sightseeingIds);
    @Query("SELECT * FROM sightseeing WHERE id = :sightseeingId")
    Sightseeing getSightseeingById(int sightseeingId);
    @Query("SELECT name FROM sightseeing WHERE id = :sightseeingId")
    String getSightseeingNameById(int sightseeingId);
    @Insert
    void insertAll(List<Sightseeing> sightseeingList);
    @Query("SELECT * FROM sightseeing WHERE id IN (:sightseeingIds)")
    List<Sightseeing> getSightseeingsByIds(List<Integer> sightseeingIds);
}
