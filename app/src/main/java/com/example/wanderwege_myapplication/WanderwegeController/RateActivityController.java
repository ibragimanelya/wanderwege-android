package com.example.wanderwege_myapplication.WanderwegeController;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.Rating;
import com.example.wanderwege_myapplication.WanderwegeModel.IRatingModel;

public class RateActivityController implements IComRatController {
    private IRatingModel IRatingModel;

    public RateActivityController(IRatingModel IRatingModel) {
        this.IRatingModel = IRatingModel;
    }

    @Override
    public void submitRating(String name, int ratingValue, int wanderwegId, Runnable onSuccess, Runnable onFailure) {
        if (name.isEmpty() || wanderwegId == -1) {
            onFailure.run();
            return;
        }

        Rating rating = new Rating();

        rating.setName(name);
        rating.setRating(ratingValue);
        rating.setWanderwegId(wanderwegId);

        IRatingModel.insertRating(rating, () -> {
            IRatingModel.calculateAndUpdateAverageRating(wanderwegId);
            onSuccess.run();
        }, onFailure);
    }

    @Override
    public void submitComment(String name, String commentText, Runnable onSuccess, Runnable onFailure) {}
}
