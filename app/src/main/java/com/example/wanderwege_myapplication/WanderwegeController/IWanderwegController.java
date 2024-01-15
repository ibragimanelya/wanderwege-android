package com.example.wanderwege_myapplication.WanderwegeController;

import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public interface IWanderwegController {
    void loadWanderwegsData();
    String getSightseeingNameById(int id);
    Wanderweg getWanderwegDetails(int id);
    void onItemClick(Wanderweg item);
    List<String> getSightseeingNames(List<Integer> ids);
    List<Integer> getSightseeingIdsByWanderweg(int wanderwegId);
    MarkOfSightseeing getMarkOfSightseeingById(int mark);
    Sightseeing getSightseeingDetails(int id);
    void loadMarkers();
    List<Wanderweg> getWanderwegList();
}
