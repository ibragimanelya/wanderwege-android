package com.example.wanderwege_myapplication.gui;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeModel.WanderwegModel;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeController.WanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeModel.SightseeingModel;

public class SSGuideActivity extends AppCompatActivity {
    private TextView titleTemplate, titleOfSSeing, buildYear, briefHistory;
    private ImageView pictureOfSS;
    private IWanderwegController wanderwegController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ss_guide_acitivity);

        initializeViews();
        wanderwegController = new WanderwegController(new WanderwegModel(AppDataBase.getInstance(this)), new SightseeingModel(AppDataBase.getInstance(this)));

        int sightseeingId = getIntent().getIntExtra("SSEING_ID", -1);
        if (sightseeingId != -1) {
            Sightseeing sightseeing = wanderwegController.getSightseeingDetails(sightseeingId);
            populateViews(sightseeing);
        }

        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        titleTemplate = findViewById(R.id.title_template);
        titleOfSSeing = findViewById(R.id.titleOfSSeing);
        buildYear = findViewById(R.id.buildYear);
        briefHistory = findViewById(R.id.briefHistory);
        pictureOfSS = findViewById(R.id.imageView);
    }

    private void populateViews(Sightseeing sightseeing) {
        titleTemplate.setText(sightseeing.getName());
        titleOfSSeing.setText(sightseeing.getName());
        buildYear.setText(String.format("Built in: %s", sightseeing.getYear()));
        briefHistory.setText(sightseeing.getHistory());

        Resources resources = getResources();
        final int resourceId = resources.getIdentifier(sightseeing.getImageIdentifier(), "drawable", getPackageName());
        pictureOfSS.setImageResource(resourceId);
    }
}
