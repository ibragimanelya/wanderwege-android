package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "wanderweg")
public class Wanderweg {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String distance;
    private String sightseeingIds;
    private int startpunkt;
    private int endpunkt;
    private String time;
    private double averageRating;
    private int markOfWWId;
    private String imageIdentifier;

    private String markOfSSeingIds;


    public Wanderweg() {};

    @Ignore
    public Wanderweg(String name, String distance, List<Integer> sightseeingList, Sightseeing startpunkt, Sightseeing endpunkt, String time, String imageIdentifier, int markOfWanderwegId, List<Integer> markOfSightseeingsList) {
        setName(name);
        setDistance(distance);
        setSightseeingIds(sightseeingList);
        setStartpunkt(startpunkt.getId());
        setEndpunkt(endpunkt.getId());
        setTime(time);
        this.imageIdentifier= imageIdentifier;
        this.markOfWWId = markOfWanderwegId;
        setMarkOfSSeingIds(markOfSightseeingsList);
        this.averageRating = 0.0;
    }

    private void setMarkOfSSeingIds(List<Integer> markOfSightseeingsList) {
        this.markOfSSeingIds = Converters.fromIdsListToStrings(markOfSightseeingsList);
    }
    public List<Integer> getMarkOfSSeingList() {
        return Converters.toIdsListFromStrings(markOfSSeingIds);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }
    public String getSightseeingIds() {
        return sightseeingIds;
    }
    public List<Integer> getSightseeingIdList() {
        return Converters.toIdsListFromStrings(this.sightseeingIds);
    }
    public void setSightseeingIds(List<Integer> sightseeingIds) {
        this.sightseeingIds = Converters.fromIdsListToStrings(sightseeingIds);
    }
    public int getStartpunkt() {
        return startpunkt;
    }

    public int getEndpunkt() {
        return endpunkt;
    }

    public String getTime() {
        return time;
    }

    public double getRating() {
        return averageRating;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void setStartpunkt(int startpunkt) {
        this.startpunkt = startpunkt;
    }

    public void setEndpunkt(int endpunkt) {
        this.endpunkt = endpunkt;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getSightseeingNames(SightseeingDao sightseeingDao) {
        return sightseeingDao.getSightseeingListOfNamesByIds(this.getSightseeingIdList());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSightseeingIds(String sightseeingIds) {
        this.sightseeingIds = sightseeingIds;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    public void setImageIdentifier(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
    }

    public int getMarkOfWWId() {
        return markOfWWId;
    }

    public void setMarkOfWWId(int markOfWWId) {
        this.markOfWWId = markOfWWId;
    }

    public String getMarkOfSSeingIds() {
        return markOfSSeingIds;
    }

    public void setMarkOfSSeingIds(String markOfSSeingIds) {
        this.markOfSSeingIds = markOfSSeingIds;
    }

}