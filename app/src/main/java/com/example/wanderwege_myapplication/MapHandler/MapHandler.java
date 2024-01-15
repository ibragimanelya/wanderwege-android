package com.example.wanderwege_myapplication.MapHandler;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.wanderwege_myapplication.gui.GuideActivity;
import com.example.wanderwege_myapplication.gui.SSGuideActivity;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;

public class MapHandler implements IMapHandler {
    public static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map;
    private Context context;

    public MapHandler(MapView map, Context context) {
        this.map = map;
        this.context = context;
        initializeMap();
    }

    public void initializeMap() {
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.setVisibility(View.VISIBLE);
    }

    @Override
    public void setMapCenter(double latitude, double longitude, int zoomLevel) {
        GeoPoint center = new GeoPoint(latitude, longitude);
        map.getController().setCenter(center);
        map.getController().setZoom(zoomLevel);
    }

    @Override
    public void addWanderwegMarkerToMap(List<MarkOfWanderweg> locations) {
        for (MarkOfWanderweg location : locations) {
            Marker marker = new Marker(map);
            marker.setPosition(new GeoPoint(location.getLatitude(), location.getLongitude()));
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

            marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker, MapView mapView) {
                    Intent intent = new Intent(context, GuideActivity.class);
                    intent.putExtra("WANDERWEG_ID", location.getId());
                    context.startActivity(intent);
                    return true;
                }
            });
            map.getOverlays().add(marker);
        }
    }

    @Override
    public void addSightseeingMarkerToMap(MarkOfSightseeing mark, int sightseeingId) {
        Marker marker = new Marker(map);
        marker.setPosition(new GeoPoint(mark.getLatitude(), mark.getLongitude()));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                // Start the SSGuideActivity
                Intent intent = new Intent(context, SSGuideActivity.class);
                intent.putExtra("SSEING_ID", sightseeingId); // Pass the ID to the next activity
                context.startActivity(intent);
                return true;
            }
        });

        map.getOverlays().add(marker);
    }

    @Override
    public void requestPermissionsIfNecessary(String[] permissions) {
        AppCompatActivity activity = (AppCompatActivity) context;
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    activity,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

}
