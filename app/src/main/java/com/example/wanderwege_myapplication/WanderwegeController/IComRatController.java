package com.example.wanderwege_myapplication.WanderwegeController;

public interface IComRatController {
    void submitComment(String name, String commentText,
                       Runnable onSuccess, Runnable onFailure);
    void submitRating(String name, int ratingValue, int wanderwegId,
                      Runnable onSuccess, Runnable onFailure);

}
