package com.example.wanderwege_myapplication.WanderwegeModel;

import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;

public class MarkOfWanderwegModel implements IMarkOfWanderwegModel {
    private AppDataBase db;

    public MarkOfWanderwegModel(AppDataBase db) {
        this.db = db;
    }

    @Override
    public List<MarkOfWanderweg> getAllWanderwegsMarks() {
        return db.markOfWanderwegDao().getAllWanderwegsMarks();
    }
}
