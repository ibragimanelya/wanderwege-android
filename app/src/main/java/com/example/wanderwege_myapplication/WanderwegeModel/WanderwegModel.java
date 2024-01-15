package com.example.wanderwege_myapplication.WanderwegeModel;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Converters;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfWanderweg;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Wanderweg;

public class WanderwegModel implements IWanderwegModel {
    private ExecutorService databaseExecutor;
    private AppDataBase appDataBase;

    public WanderwegModel(AppCompatActivity activity) {
        setAppDataBase(activity);
    }

    public WanderwegModel(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
        this.databaseExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public List<Wanderweg> getAllWanderwegs() {
        return appDataBase.wanderwegDao().getAllWanderwegs();
    }

    @Override
    public Wanderweg getWanderwegById(int wanderwegId) {
        return appDataBase.wanderwegDao().getWanderwegById(wanderwegId);
    }

    @Override
    public List<Integer> getSightseeingIdsByWanderwegId(int wanderwegId) {
        String sightseeingIdsString = appDataBase.wanderwegDao().getSSingIdsByWanderwegId(wanderwegId);
        return Converters.toIdsListFromStrings(sightseeingIdsString);
    }

    public void setAppDataBase(AppCompatActivity activity) {
        this.appDataBase = AppDataBase.getInstance(activity);
    }

    @Override
    public void createAndAddWanderwegs() {
        databaseExecutor.execute(() -> {
            List<Wanderweg> wanderwegList = new ArrayList<>();

            // Berlin
            List<Integer> berlinSightseeingIds = new ArrayList<>();
            berlinSightseeingIds.add(1); // ID for Brandenburg Gate
            berlinSightseeingIds.add(2); // ID for Reichstag Building
            berlinSightseeingIds.add(3); // ID for Checkpoint Charlie
            berlinSightseeingIds.add(4); // ID for Berlin Wall Memorial

            List<Integer> berlinMarkOfSSeingsIDs = new ArrayList<>();
            berlinMarkOfSSeingsIDs.add(1);
            berlinMarkOfSSeingsIDs.add(2);
            berlinMarkOfSSeingsIDs.add(3);
            berlinMarkOfSSeingsIDs.add(4);

            List<Sightseeing> berlinSightseeings = appDataBase.sightseeingDao().getSightseeingsByIds(berlinSightseeingIds);

            Wanderweg berlinWanderweg = new Wanderweg("Historical Highlights",
                    "Approximately 3 miles", berlinSightseeingIds,
                    berlinSightseeings.get(0), berlinSightseeings.get(3), "2-3 hours", "berlin_wanderweg", 1, berlinMarkOfSSeingsIDs);

            wanderwegList.add(berlinWanderweg);

            // Milan
            List<Integer> milanSightseeingIds = new ArrayList<>();
            milanSightseeingIds.add(5); // ID for Milan Cathedral (Duomo di Milano)
            milanSightseeingIds.add(6); // ID for Galleria Vittorio Emanuele II
            milanSightseeingIds.add(7); // ID for Teatro alla Scala
            milanSightseeingIds.add(8); // ID for Sforza Castle (Castello Sforzesco)

            List<Integer> milanMarkOfSSeingsIDs = new ArrayList<>();
            milanMarkOfSSeingsIDs.add(5);
            milanMarkOfSSeingsIDs.add(6);
            milanMarkOfSSeingsIDs.add(7);
            milanMarkOfSSeingsIDs.add(8);

            List<Sightseeing> milanSightseeings = appDataBase.sightseeingDao().getSightseeingsByIds(milanSightseeingIds);

            Wanderweg milanWanderweg = new Wanderweg("Art Expedition",
                    "Around 2.5 miles", milanSightseeingIds,
                    milanSightseeings.get(0), milanSightseeings.get(3), "2 hours", "milan_wanderweg", 2, milanMarkOfSSeingsIDs);

            wanderwegList.add(milanWanderweg);

            // Barcelona
            List<Integer> barcelonaSightseeingIds = new ArrayList<>();
            barcelonaSightseeingIds.add(9); // ID for Casa Batlló
            barcelonaSightseeingIds.add(10); // ID for La Pedrera (Casa Milà)
            barcelonaSightseeingIds.add(11); // ID for Sagrada Família
            barcelonaSightseeingIds.add(12); // ID for Park Güell

            List<Integer> barcelonaMarkOfSSeingsIDs = new ArrayList<>();
            barcelonaMarkOfSSeingsIDs.add(9);
            barcelonaMarkOfSSeingsIDs.add(10);
            barcelonaMarkOfSSeingsIDs.add(11);
            barcelonaMarkOfSSeingsIDs.add(12);

            List<Sightseeing> barcelonaSightseeings = appDataBase.sightseeingDao().getSightseeingsByIds(barcelonaSightseeingIds);
            Wanderweg barcelonaWanderweg = new Wanderweg("Gaudi's Masterpieces",
                    "Approximately 3.5 miles", barcelonaSightseeingIds,
                    barcelonaSightseeings.get(0), barcelonaSightseeings.get(3), "3 hours", "barcelona_wanderweg1", 3, barcelonaMarkOfSSeingsIDs);
            wanderwegList.add(barcelonaWanderweg);

            // Paris
            List<Integer> parisSightseeingIds = new ArrayList<>();
            parisSightseeingIds.add(13); // ID for Louvre Museum
            parisSightseeingIds.add(14); // ID for Tuileries Garden
            parisSightseeingIds.add(15); // ID for Musée d'Orsay
            parisSightseeingIds.add(16); // ID for Notre-Dame Cathedral

            List<Integer> parisMarkOfSSeingsIDs = new ArrayList<>();
            parisMarkOfSSeingsIDs.add(13);
            parisMarkOfSSeingsIDs.add(14);
            parisMarkOfSSeingsIDs.add(15);
            parisMarkOfSSeingsIDs.add(16);

            List<Sightseeing> parisSightseeings = appDataBase.sightseeingDao().getSightseeingsByIds(parisSightseeingIds);
            Wanderweg parisWanderweg = new Wanderweg("Parisian Promenade",
                    "Around 2.8 miles", parisSightseeingIds,
                    parisSightseeings.get(0), parisSightseeings.get(3), "2.5 hours", "paris_wanderweg", 4, parisMarkOfSSeingsIDs);
            wanderwegList.add(parisWanderweg);

            appDataBase.wanderwegDao().insertAll(wanderwegList);
        });
    }

    @Override
    public void addGeoPointsForWanderwegs() {
        databaseExecutor.execute(() -> {
            GeoPoint berlinWanderwegGeopoint = new GeoPoint(52.5200, 13.4050);
            GeoPoint milanWanderwegGeopoint = new GeoPoint(45.4642, 9.1900);
            GeoPoint barcelonaWanderwegGeopoint = new GeoPoint(41.3851, 2.1734);
            GeoPoint parisWanderwegGeopoint = new GeoPoint(48.8566, 3.3522);

            appDataBase.markOfWanderwegDao().insert(new MarkOfWanderweg(berlinWanderwegGeopoint.getLongitude(), berlinWanderwegGeopoint.getLatitude()));
            appDataBase.markOfWanderwegDao().insert(new MarkOfWanderweg(milanWanderwegGeopoint.getLongitude(), milanWanderwegGeopoint.getLatitude()));
            appDataBase.markOfWanderwegDao().insert(new MarkOfWanderweg(barcelonaWanderwegGeopoint.getLongitude(), barcelonaWanderwegGeopoint.getLatitude()));
            appDataBase.markOfWanderwegDao().insert(new MarkOfWanderweg(parisWanderwegGeopoint.getLongitude(), parisWanderwegGeopoint.getLatitude()));
        });
    }
}
