package com.example.wanderwege_myapplication.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanderwege_myapplication.R;

public class CommentAndRateActivity extends AppCompatActivity {
    private int wanderwegId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_and_rate_page_activity);

        wanderwegId = getIntent().getIntExtra("WANDERWEG_ID", -1);

        setupRatingButton();
        setupCommentButton();
        setupBackButton();
    }

    private void setupRatingButton() {
        Button ratingButton = findViewById(R.id.rateExperience);
        ratingButton.setOnClickListener(v -> navigateToRateActivity());
    }

    private void setupCommentButton() {
        Button commentButton = findViewById(R.id.leaveComment);
        commentButton.setOnClickListener(v -> navigateToCommentActivity());
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());
    }

    private void navigateToRateActivity() {
        Intent intent = new Intent(this, RateActivity.class);
        intent.putExtra("WANDERWEG_ID", wanderwegId);
        startActivity(intent);
    }

    private void navigateToCommentActivity() {
        Intent intent = new Intent(this, CommentActivity.class);
        startActivity(intent);
    }
}
