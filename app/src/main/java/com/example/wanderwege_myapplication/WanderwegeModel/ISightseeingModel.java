package com.example.wanderwege_myapplication.WanderwegeModel;

import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;

public interface ISightseeingModel {
    Sightseeing getSightseeingById(int id);
    String getSightseeingNameById(int id);
    List<String> getSightseeingNames(List<Integer> ids);
    MarkOfSightseeing getMarkOfSightseeingById(int markId);
    void createAndAddSightseeings();
    void addGeoPointsForSightseeings();
}
