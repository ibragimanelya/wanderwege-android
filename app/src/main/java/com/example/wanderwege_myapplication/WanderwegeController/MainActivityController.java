package com.example.wanderwege_myapplication.WanderwegeController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wanderwege_myapplication.WanderwegeModel.ISightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeModel.SightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeModel.WanderwegModel;

public class MainActivityController {
    private ISightseeingModel sightseeingModel;
    private WanderwegModel wanderwegModel;
    private ExecutorService databaseExecutor;

    public MainActivityController(AppDataBase db) {
        this.sightseeingModel = new SightseeingModel(db);
        this.wanderwegModel = new WanderwegModel(db);
        this.databaseExecutor = Executors.newSingleThreadExecutor();
    }

    public void loadSightseeings() {
        sightseeingModel.createAndAddSightseeings();
    }

    public void loadWanderwegs() {
        wanderwegModel.createAndAddWanderwegs();
    }

    public void loadGeoPointsForSightseeings() {
        sightseeingModel.addGeoPointsForSightseeings();
    }

    public void loadGeoPointsForWanderwegs() {
        wanderwegModel.addGeoPointsForWanderwegs();
    }
    public void onDestroy() {
        databaseExecutor.shutdown();
    }
}
