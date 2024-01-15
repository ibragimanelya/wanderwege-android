package com.example.wanderwege_myapplication.gui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.CommentActivityController;
import com.example.wanderwege_myapplication.WanderwegeController.IComRatController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeModel.CommentModel;

public class CommentActivity extends AppCompatActivity {
    private EditText editTextName, editTextComment;
    private IComRatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page_activity);

        initializeViews();
        controller = new CommentActivityController(new CommentModel(AppDataBase.getInstance(this)));

        ImageView backButton = findViewById(R.id.buttonBack);
        backButton.setOnClickListener(v -> finish());
    }

    private void initializeViews() {
        editTextName = findViewById(R.id.enter_name_in_comment_page);
        editTextComment = findViewById(R.id.enter_comment_in_comment_page);
    }

    public void submitInfoOnCommentPage(View view) {
        String name = editTextName.getText().toString().trim();
        String commentText = editTextComment.getText().toString().trim();

        controller.submitComment(name, commentText, this::showSuccessDialog, this::showFailureDialog);
    }

    private void showSuccessDialog() {
        runOnUiThread(() -> new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Your comment has been saved successfully!")
                .setPositiveButton("OK", (dialog, which) -> {
                    editTextName.setText("");
                    editTextComment.setText("");
                    dialog.dismiss();
                })
                .show()
        );
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
