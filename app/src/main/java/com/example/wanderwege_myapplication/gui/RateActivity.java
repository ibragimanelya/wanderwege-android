package com.example.wanderwege_myapplication.gui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.IComRatController;
import com.example.wanderwege_myapplication.WanderwegeController.RateActivityController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeModel.RatingModel;

public class RateActivity extends AppCompatActivity {

    private EditText nameEditText;
    private IComRatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_page_activity);

        nameEditText = findViewById(R.id.name_rating);
        controller = new RateActivityController(new RatingModel(AppDataBase.getInstance(this)));

        setupRatingButtons();
        setupBackButton();
    }

    private void setupRatingButtons() {
        int[] buttonIds = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5};
        for (int i = 0; i < buttonIds.length; i++) {
            int ratingValue = i + 1;
            Button button = findViewById(buttonIds[i]);
            button.setOnClickListener(v -> submitRating(ratingValue));
        }
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());
    }

    private void submitRating(int ratingValue) {
        String name = nameEditText.getText().toString().trim();
        int wanderwegId = getIntent().getIntExtra("WANDERWEG_ID", -1);

        controller.submitRating(name, ratingValue, wanderwegId, this::showSuccessDialog, this::showFailureDialog);
    }

    private void showSuccessDialog() {
        runOnUiThread(() -> new AlertDialog.Builder(RateActivity.this)
                .setTitle("Success")
                .setMessage("Your rating has been saved successfully!")
                .setPositiveButton("OK", (dialog, which) -> {
                    nameEditText.setText("");
                    dialog.dismiss();
                })
                .show());
    }

    private void showFailureDialog() {
        runOnUiThread(() -> new AlertDialog.Builder(this)
                .setTitle("Incomplete Information")
                .setMessage("Please enter both your name and your comment.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show()
        );
    }

}
