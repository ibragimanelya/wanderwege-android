package com.example.wanderwege_myapplication.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.MainActivityController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;

public class MainActivity extends AppCompatActivity {
    private MainActivityController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity); // Das Layout für die MainActivity

        controller = new MainActivityController(AppDataBase.getInstance(this));

        // Button für die Auswahl aus der Karte
        Button mapButton = findViewById(R.id.buttonMap);
        mapButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MapActivity.class)));

        // Button für die Auswahl aus der Liste
        Button listButton = findViewById(R.id.buttonList);
        listButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListActivity.class)));

        controller.loadSightseeings();
        controller.loadWanderwegs();
        controller.loadGeoPointsForSightseeings();
        controller.loadGeoPointsForWanderwegs();

        controller.onDestroy();
    }
}