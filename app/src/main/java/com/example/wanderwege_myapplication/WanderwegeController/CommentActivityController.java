package com.example.wanderwege_myapplication.WanderwegeController;

import com.example.wanderwege_myapplication.WanderwegeModel.ICommentModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Comment;

public class CommentActivityController implements IComRatController {
    private ICommentModel model;
    public CommentActivityController(ICommentModel model) {
        this.model = model;
    }

    @Override
    public void submitComment(String name, String commentText, Runnable onSuccess, Runnable onFailure) {
        if (name.isEmpty() || commentText.isEmpty()) {
            onFailure.run();
            return;
        }

        Comment comment = new Comment();
        comment.setName(name);
        comment.setComment(commentText);
        model.insertComment(comment, onSuccess, onFailure);
    }

    @Override
    public void submitRating(String name, int ratingValue, int wanderwegId, Runnable onSuccess, Runnable onFailure) {}

}
