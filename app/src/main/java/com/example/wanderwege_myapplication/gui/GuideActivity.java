package com.example.wanderwege_myapplication.gui;

import static com.example.wanderwege_myapplication.MapHandler.MapHandler.REQUEST_PERMISSIONS_REQUEST_CODE;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.wanderwege_myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;

import java.util.ArrayList;
import java.util.List;

import com.example.wanderwege_myapplication.MapHandler.IMapHandler;
import com.example.wanderwege_myapplication.MapHandler.MapHandler;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeController.WanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public class GuideActivity extends AppCompatActivity {
    private TextView templateTitle, titleOfGuide, distance, startPoint, endPoint, sseingListTitle;
    private ListView sightseeingList;
    private MapView map;
    private FloatingActionButton addComRatButton;
    private IWanderwegController wanderwegController;
    private IMapHandler mapHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity);

        initializeViews();

        AppDataBase database = AppDataBase.getInstance(this);
        wanderwegController = new WanderwegController(GuideActivity.this, database);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        map = findViewById(R.id.mapView);
        mapHandler = new MapHandler(map, this);

        mapHandler.requestPermissionsIfNecessary(new String[]{
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );

        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());

        int wanderwegId = getIntent().getIntExtra("WANDERWEG_ID", -1);
        if (wanderwegId != -1) {
            loadWanderwegDetails(wanderwegId);
        }

        addComRatButton = findViewById(R.id.addComRatButton);
        addComRatButton.setOnClickListener(v -> {
            Intent comRatIntent = new Intent(GuideActivity.this, CommentAndRateActivity.class);
            comRatIntent.putExtra("WANDERWEG_ID", wanderwegId);
            startActivity(comRatIntent);
        });


    }

    private void initializeViews() {
        templateTitle = findViewById(R.id.title_template);
        titleOfGuide = findViewById(R.id.titleOfGuide);
        distance = findViewById(R.id.distance);
        sseingListTitle = findViewById(R.id.titleOfGuide);
        sightseeingList = findViewById(R.id.listOfSSeings);
        startPoint = findViewById(R.id.startPunkt);
        endPoint = findViewById(R.id.endPunkt);
    }

    private void loadWanderwegDetails(int wanderwegId) {
        Wanderweg wanderweg = wanderwegController.getWanderwegDetails(wanderwegId);
        if (wanderweg != null) {
            templateTitle.setText(wanderweg.getName());
            titleOfGuide.setText(wanderweg.getName());
            distance.setText(wanderweg.getDistance());
            startPoint.setText(String.format("Startpunkt: %s", wanderwegController.getSightseeingNameById(wanderweg.getStartpunkt())));
            endPoint.setText(String.format("Endpunkt: %s", wanderwegController.getSightseeingNameById(wanderweg.getEndpunkt())));

            List<Integer> sightseeingIds = wanderwegController.getSightseeingIdsByWanderweg(wanderwegId);

            displaySightseeingMarkers(sightseeingIds);
            updateSightseeingListView(sightseeingIds);
        }
    }

    private void displaySightseeingMarkers(List<Integer> sightseeingIds) {
        if (!sightseeingIds.isEmpty()) {
            MarkOfSightseeing firstSightseeing = wanderwegController.getMarkOfSightseeingById(sightseeingIds.get(0));
            if (firstSightseeing != null) {
                setMapCenter(firstSightseeing);
            }
            for (Integer sightseeingId : sightseeingIds) {
                MarkOfSightseeing sightseeing = wanderwegController.getMarkOfSightseeingById(sightseeingId);
                if (sightseeing != null) {
                    addMarkerToMap(sightseeing, sightseeingId);
                }
            }
        }
    }

    private void addMarkerToMap(MarkOfSightseeing mark, Integer sightseeingId) {
        mapHandler.addSightseeingMarkerToMap(mark, sightseeingId);
    }


    private void setMapCenter(MarkOfSightseeing mark) {
        mapHandler.setMapCenter(mark.getLatitude(), mark.getLongitude(), 13);
    }

    private void updateSightseeingListView(List<Integer> sightseeingIds) {
        List<String> sightseeingNames = wanderwegController.getSightseeingNames(sightseeingIds);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sightseeingNames);
        sightseeingList.setAdapter(adapter);

        sightseeingList.setOnItemClickListener((parent, view, position, id) -> {
            Integer sightseeingId = sightseeingIds.get(position);
            Intent intent = new Intent(GuideActivity.this, SSGuideActivity.class);
            intent.putExtra("SSEING_ID", sightseeingId);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

}
