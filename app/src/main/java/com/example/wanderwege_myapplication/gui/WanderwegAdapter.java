package com.example.wanderwege_myapplication.gui;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.wanderwege_myapplication.R;
import com.example.wanderwege_myapplication.WanderwegeController.IWanderwegController;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Converters;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public class WanderwegAdapter extends RecyclerView.Adapter<WanderwegAdapter.ViewHolder> {

    private final Context context;
    private List<Wanderweg> wanderwegList;
    private IWanderwegController wanderwegController;
    public interface OnItemClickListener {
        void onItemClick(Wanderweg item);
    }
    private final OnItemClickListener listener;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView guideName, distance, startFinish, sightseeing, averageRating;

        public ViewHolder(View itemView, OnItemClickListener listener, Wanderweg wanderweg) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_wanderweg);
            distance = itemView.findViewById(R.id.distance_in_km);
            guideName = itemView.findViewById(R.id.text_guide_name);
            startFinish = itemView.findViewById(R.id.text_start_finish);
            sightseeing = itemView.findViewById(R.id.text_sightseeing);
            averageRating = itemView.findViewById(R.id.ratingButton_text);

            itemView.setOnClickListener(v -> listener.onItemClick(wanderweg));
        }
    }

    public WanderwegAdapter(Context context, List<Wanderweg> wanderwegList, OnItemClickListener listener, IWanderwegController wanderwegController) {
        this.context = context;
        this.wanderwegList = wanderwegList;
        this.listener = listener;
        this.wanderwegController = wanderwegController;
    }

    @Override
    public WanderwegAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(v, listener, wanderwegList.get(viewType));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Wanderweg wanderweg = wanderwegList.get(position);

        holder.guideName.setText(wanderweg.getName());
        holder.distance.setText(wanderweg.getDistance());

        String startSightseeingName = wanderwegController.getSightseeingNameById(wanderweg.getStartpunkt());
        String endSightseeingName = wanderwegController.getSightseeingNameById(wanderweg.getEndpunkt());
        holder.startFinish.setText("A: " + startSightseeingName+ ", B: " + endSightseeingName);

        String imageName = wanderweg.getImageIdentifier();
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        if (imageResId != 0) { // Resource ID found
            holder.imageView.setImageResource(imageResId);
        }

        String sightseeingIdsString = wanderweg.getSightseeingIds();
        List<Integer> sightseeingIds = Converters.toIdsListFromStrings(sightseeingIdsString);

        List<String> sightseeingNames = wanderwegController.getSightseeingNames(sightseeingIds);
        String sightseeingNamesStr = TextUtils.join(", ", sightseeingNames);
        holder.sightseeing.setText(sightseeingNamesStr);
        holder.averageRating.setText(String.format("%.1f", wanderweg.getAverageRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(wanderweg);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wanderwegList.size();
    }
}
