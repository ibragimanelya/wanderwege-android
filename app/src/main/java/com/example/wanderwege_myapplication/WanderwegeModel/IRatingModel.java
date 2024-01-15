package com.example.wanderwege_myapplication.WanderwegeModel;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.Rating;

public interface IRatingModel {
    void insertRating(Rating rating, Runnable onSucces, Runnable onFailure);
    void calculateAndUpdateAverageRating(int wanderwegId);
}
