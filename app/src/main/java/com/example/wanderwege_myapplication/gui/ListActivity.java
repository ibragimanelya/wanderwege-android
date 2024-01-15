package com.example.wanderwege_myapplication.gui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeController.WanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public class ListActivity extends AppCompatActivity implements WanderwegAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ImageView backButton;
    private IWanderwegController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_with_list_activity); // Das Layout für die MainActivity

        recyclerView = findViewById(R.id.recycler_view_wanderweg_card);
        backButton = findViewById(R.id.buttonBack);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDataBase database = AppDataBase.getInstance(this);
        controller = new WanderwegController(this, database);
        controller.loadWanderwegsData();

        backButton.setOnClickListener(v -> finish());
    }

    public void updateWanderwegList(List<Wanderweg> wanderwegs) {
        WanderwegAdapter adapter = new WanderwegAdapter(this, wanderwegs, this, controller);
        recyclerView.setAdapter(adapter);
    }

    public void navigateToGuideActivity(int wanderwegId) {
        Intent intent = new Intent(ListActivity.this, GuideActivity.class);
        intent.putExtra("WANDERWEG_ID", wanderwegId);
        startActivity(intent);
    }
    @Override
    public void onItemClick(Wanderweg item) {
        controller.onItemClick(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.loadWanderwegsData();
    }

    public void setMockController(IWanderwegController controller) {
        this.controller = controller;
        List<Wanderweg> mockWanderwegList = controller.getWanderwegList(); // Get the mock list
        updateWanderwegList(mockWanderwegList); // This method should update the RecyclerView with the mocked data
    }


}
