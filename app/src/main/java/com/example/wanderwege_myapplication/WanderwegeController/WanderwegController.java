package com.example.wanderwege_myapplication.WanderwegeController;

import com.example.wanderwege_myapplication.WanderwegeModel.ISightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeModel.IWanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeModel.MarkOfWanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeModel.SightseeingModel;
import com.example.wanderwege_myapplication.WanderwegeModel.WanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;
import com.example.wanderwege_myapplication.gui.GuideActivity;
import com.example.wanderwege_myapplication.gui.ListActivity;
import com.example.wanderwege_myapplication.gui.MapActivity;

import java.util.List;

public class WanderwegController implements IWanderwegController {
    private MarkOfWanderwegModel markOfWanderwegModel;
    private MapActivity mapView;
    private IWanderwegModel wanderwegModel;
    private ListActivity view;
    private GuideActivity viewG;
    private ISightseeingModel sightseeingModel;

    public WanderwegController(ListActivity view, AppDataBase database) {
        this.view = view;
        this.wanderwegModel = new WanderwegModel(view);
        this.sightseeingModel = new SightseeingModel(database);
    }

    public WanderwegController(GuideActivity viewG, AppDataBase appDataBase) {
        this.viewG = viewG;
        this.wanderwegModel = new WanderwegModel(view);
        this.sightseeingModel = new SightseeingModel(appDataBase);
    }

    public WanderwegController(IWanderwegModel wanderwegModel, ISightseeingModel sightseeingModel) {
        this.wanderwegModel = wanderwegModel;
        this.sightseeingModel = sightseeingModel;
    }

    public WanderwegController (MapActivity view) {
        this.mapView = view;
        AppDataBase db = AppDataBase.getInstance(view);
        this.markOfWanderwegModel = new MarkOfWanderwegModel(db);
    }

    @Override
    public void loadWanderwegsData() {
        List<Wanderweg> wanderwegs = wanderwegModel.getAllWanderwegs();
        view.updateWanderwegList(wanderwegs);
    }

    @Override
    public String getSightseeingNameById(int id) {
        return sightseeingModel.getSightseeingNameById(id);
    }

    @Override
    public Wanderweg getWanderwegDetails(int id) {
        return wanderwegModel.getWanderwegById(id);
    }

    @Override
    public void onItemClick(Wanderweg item) {
        view.navigateToGuideActivity(item.getId());
    }

    @Override
    public List<String> getSightseeingNames(List<Integer> ids) {
        return sightseeingModel.getSightseeingNames(ids);
    }

    @Override
    public List<Integer> getSightseeingIdsByWanderweg(int wanderwegId) {
        return wanderwegModel.getSightseeingIdsByWanderwegId(wanderwegId);
    }

    @Override
    public MarkOfSightseeing getMarkOfSightseeingById(int mark) {
        return sightseeingModel.getMarkOfSightseeingById(mark);
    }

    @Override
    public Sightseeing getSightseeingDetails(int id) {
        return sightseeingModel.getSightseeingById(id);
    }

    @Override
    public void loadMarkers() {
        List<MarkOfWanderweg> markers = markOfWanderwegModel.getAllWanderwegsMarks();
        mapView.addMarkersToMap(markers);
    }

    @Override
    public List<Wanderweg> getWanderwegList() {
        return wanderwegModel.getAllWanderwegs();
    }

}
