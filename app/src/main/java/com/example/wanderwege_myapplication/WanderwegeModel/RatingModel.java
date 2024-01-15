package com.example.wanderwege_myapplication.WanderwegeModel;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Rating;

public class RatingModel implements IRatingModel {
    private AppDataBase db;
    private ExecutorService executorService;

    public RatingModel(AppDataBase db) {
        this.db = db;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void insertRating(Rating rating, Runnable onSuccess, Runnable onFailure) {
        executorService.execute(() -> {
            try {
                db.ratingDao().insertRating(rating);
                onSuccess.run();
            } catch (Exception e) {
                onFailure.run();
            }
        });
    }

    @Override
    public void calculateAndUpdateAverageRating(int wanderwegId) {
        executorService.execute(() -> {
            List<Rating> ratingsForWanderweg = db.ratingDao().getRatingsForWanderweg(wanderwegId);
            double averageRating = ratingsForWanderweg.stream()
                    .mapToInt(Rating::getRating)
                    .average()
                    .orElse(0.0);
            db.ratingDao().updateAverageRating(wanderwegId, averageRating);
        });
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

}
