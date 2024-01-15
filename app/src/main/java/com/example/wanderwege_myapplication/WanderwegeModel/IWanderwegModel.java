package com.example.wanderwege_myapplication.WanderwegeModel;

import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public interface IWanderwegModel {
    List<Wanderweg> getAllWanderwegs();
    Wanderweg getWanderwegById(int id);
    List<Integer> getSightseeingIdsByWanderwegId(int wanderwegId);
    void createAndAddWanderwegs();
    void addGeoPointsForWanderwegs();
}
