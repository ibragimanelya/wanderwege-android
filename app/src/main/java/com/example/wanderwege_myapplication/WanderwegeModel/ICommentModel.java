package com.example.wanderwege_myapplication.WanderwegeModel;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.Comment;

public interface ICommentModel {
    void insertComment(Comment comment, Runnable onSuccess,
                       Runnable onFailure);
}
