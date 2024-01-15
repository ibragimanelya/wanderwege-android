package com.example.wanderwege_myapplication.WanderwegeModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Comment;

public class CommentModel implements ICommentModel {
    private AppDataBase appDataBase;
    private ExecutorService executorService;

    public CommentModel(AppDataBase db) {
        this.appDataBase = db;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void insertComment(Comment comment, Runnable onSuccess, Runnable onFailure) {
        executorService.execute(() -> {
            try {
                appDataBase.commentDao().insertComment(comment);
                onSuccess.run();
            } catch (Exception e) {
                onFailure.run();
            }
        });
    }
}
