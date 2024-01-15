package com.example.wanderwege_myapplication.MapHandler;

import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;

public interface IMapHandler {
    void setMapCenter(double latitude, double longitude, int zoomLevel);
    void addWanderwegMarkerToMap(List<MarkOfWanderweg> locations);
    void addSightseeingMarkerToMap(MarkOfSightseeing markOfSightseeing, int sightseeingId);
    void requestPermissionsIfNecessary(String[] permissions);
}
