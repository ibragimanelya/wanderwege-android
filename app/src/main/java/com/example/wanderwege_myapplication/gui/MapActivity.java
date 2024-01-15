package com.example.wanderwege_myapplication.gui;

import static com.example.wanderwege_myapplication.MapHandler.MapHandler.REQUEST_PERMISSIONS_REQUEST_CODE;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;

import com.example.wanderwege_myapplication.MapHandler.MapHandler;
import com.example.wanderwege_myapplication.MapHandler.IMapHandler;
import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeController.WanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;

public class MapActivity extends AppCompatActivity{
    private IWanderwegController controller;
    private MapView map;
    private IMapHandler mapHandler;
    private MyLocationNewOverlay mLocationOverlay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //inflate and create the map
        setContentView(R.layout.page_with_map_activity);

        map = (MapView) findViewById(R.id.map);
        mapHandler = new MapHandler(map, this);

        mapHandler.requestPermissionsIfNecessary(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}
        );

        GeoPoint centerOfEurope = new GeoPoint(50.1109, 10.1500);
        mapHandler.setMapCenter(centerOfEurope.getLatitude(), centerOfEurope.getLongitude(), 5);

        controller = new WanderwegController(this);
        controller.loadMarkers();

        this.mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(this), map);
        this.mLocationOverlay.enableMyLocation();
        map.getOverlays().add(this.mLocationOverlay);

        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());
    }

    public void addMarkersToMap(List<MarkOfWanderweg> locations) {
        mapHandler.addWanderwegMarkerToMap(locations);
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