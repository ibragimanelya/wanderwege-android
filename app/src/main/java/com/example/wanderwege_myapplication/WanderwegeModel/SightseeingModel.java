package com.example.wanderwege_myapplication.WanderwegeModel;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.wanderwege_myapplication.WanderwegeSpeicher.AppDataBase;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.MarkOfSightseeing;
import com.example.wanderwege_myapplication.WanderwegeSpeicher.Sightseeing;

public class SightseeingModel implements ISightseeingModel {
    private AppDataBase appDataBase;
    private ExecutorService databaseExecutor;

    public SightseeingModel(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
        this.databaseExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public Sightseeing getSightseeingById(int id) {
        return appDataBase.sightseeingDao().getSightseeingById(id);
    }

    @Override
    public String getSightseeingNameById(int id) {
        return appDataBase.sightseeingDao().getSightseeingNameById(id);
    }
    @Override
    public List<String> getSightseeingNames(List<Integer> ids) {
        List<String> names = new ArrayList<>();
        for (Integer id : ids) {
            names.add(getSightseeingNameById(id));
        }
        return names;
    }
    @Override
    public MarkOfSightseeing getMarkOfSightseeingById(int markId) {
        MarkOfSightseeing markOfSightseeing = appDataBase.markOfSightseeingDao().getMarkOfSightseeingById(markId);
        return markOfSightseeing;
    }

    @Override
    public void createAndAddSightseeings() {
        databaseExecutor.execute(() -> {
            List<Sightseeing> sightseeingList = new ArrayList<>();

            Sightseeing brandenburgGate = new Sightseeing("Brandenburg Gate", "Late 18th century", "Symbolizes German unity and peace", 1, "brandgate");
            Sightseeing reichstagBuilding = new Sightseeing("Reichstag Building", "1894", "Seat of the German Parliament", 2, "reichstag");
            Sightseeing checkpointCharlie = new Sightseeing("Checkpoint Charlie", "Cold War era", "Famous Cold War crossing point between East and West Berlin", 3, "checkpointcharlie");
            Sightseeing berlinWallMemorial = new Sightseeing("Berlin Wall Memorial", "20th century", "A reminder of the city's division, offering historical insights", 4, "berlinwall");

            sightseeingList.add(brandenburgGate);
            sightseeingList.add(reichstagBuilding);
            sightseeingList.add(checkpointCharlie);
            sightseeingList.add(berlinWallMemorial);

            Sightseeing milanCathedral = new Sightseeing("Milan Cathedral (Duomo di Milano)", "1386", "This Gothic marvel took centuries to complete", 5, "milanoduomo");
            Sightseeing galleriaVittorioEmanueleII = new Sightseeing("Galleria Vittorio Emanuele II", "19th century", "An opulent shopping arcade", 6, "galeriemilano");
            Sightseeing teatroAllaScala = new Sightseeing("Teatro alla Scala", "1778", "One of the world's most renowned opera houses", 7, "theatreallascala");
            Sightseeing sforzaCastle = new Sightseeing("Sforza Castle (Castello Sforzesco)", "15th century", "Houses several museums", 8, "castillos");

            sightseeingList.add(milanCathedral);
            sightseeingList.add(galleriaVittorioEmanueleII);
            sightseeingList.add(teatroAllaScala);
            sightseeingList.add(sforzaCastle);

            Sightseeing casaBatllo = new Sightseeing("Casa Batlló", "1906", "Designed by Antoni Gaudí, showcasing Modernisme style", 9, "casabatllo");
            Sightseeing laPedrera = new Sightseeing("La Pedrera (Casa Milà)", "1912", "Another Gaudí masterpiece, a UNESCO World Heritage Site", 10, "casamila");
            Sightseeing sagradaFamilia = new Sightseeing("Sagrada Família", "1882", "Gaudí's ongoing masterpiece, a unique basilica with intricate designs", 11, "sagradafamilia");
            Sightseeing parkGuell = new Sightseeing("Park Güell", "Early 20th century", "Gaudí's park with stunning architecture and panoramic views", 12, "parkguell");

            sightseeingList.add(casaBatllo);
            sightseeingList.add(laPedrera);
            sightseeingList.add(sagradaFamilia);
            sightseeingList.add(parkGuell);

            Sightseeing louvreMuseum = new Sightseeing("Louvre Museum", "Late 12th century", "Originally built as a fortress, now a world-famous museum", 13, "louvre");
            Sightseeing tuileriesGarden = new Sightseeing("Tuileries Garden", "16th century", "A beautiful public garden adjacent to the Louvre", 14, "tuileriesgarden");
            Sightseeing museeDOrsay = new Sightseeing("Musée d'Orsay", "Late 19th century", "Housed in a former railway station, it showcases Impressionist and Post-Impressionist art", 15, "museedorsay");
            Sightseeing notreDameCathedral = new Sightseeing("Notre-Dame Cathedral", "1163", "A stunning example of French Gothic architecture", 16, "notredame");

            sightseeingList.add(louvreMuseum);
            sightseeingList.add(tuileriesGarden);
            sightseeingList.add(museeDOrsay);
            sightseeingList.add(notreDameCathedral);

            appDataBase.sightseeingDao().insertAll(sightseeingList);
        });
    }

    @Override
    public void addGeoPointsForSightseeings() {
        databaseExecutor.execute(() -> {
            GeoPoint brandenburgGateGeoPoint = new GeoPoint(52.5163, 13.3777);
            GeoPoint reichstagBuildingGeoPoint = new GeoPoint(52.5186, 13.3763);
            GeoPoint checkpointCharlieGeoPoint = new GeoPoint(52.5075, 13.3904);
            GeoPoint berlinWallMemorialGeoPoint = new GeoPoint(52.50445415, 13.440839);

            // Milan Sightseeing GeoPoints
            GeoPoint milanCathedralGeoPoint = new GeoPoint(45.4641, 9.1916);
            GeoPoint galleriaVittorioEmanueleIIGeoPoint = new GeoPoint(45.4641, 9.1906);
            GeoPoint teatroAllaScalaGeoPoint = new GeoPoint(45.4674, 9.1896);
            GeoPoint sforzaCastleGeoPoint = new GeoPoint(45.4700, 9.1805);

            // Barcelona Sightseeing GeoPoints
            GeoPoint casaBatlloGeoPoint = new GeoPoint(41.3916, 2.1635);
            GeoPoint laPedreraGeoPoint = new GeoPoint(41.3954, 2.1619);
            GeoPoint sagradaFamiliaGeoPoint = new GeoPoint(41.4036, 2.1744);
            GeoPoint parkGuellGeoPoint = new GeoPoint(41.4147, 2.1522);

            // Paris Sightseeing GeoPoints
            GeoPoint louvreMuseumGeoPoint = new GeoPoint(48.8606, 2.3376);
            GeoPoint tuileriesGardenGeoPoint = new GeoPoint(48.8634, 2.3275);
            GeoPoint museeDOrsayGeoPoint = new GeoPoint(48.8609, 2.3258);
            GeoPoint notreDameCathedralGeoPoint = new GeoPoint(48.8529, 2.3499);

            // Create and add MarkOfSightseeing instances with GeoPoints
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(brandenburgGateGeoPoint.getLatitude(), brandenburgGateGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(reichstagBuildingGeoPoint.getLatitude(), reichstagBuildingGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(checkpointCharlieGeoPoint.getLatitude(), checkpointCharlieGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(berlinWallMemorialGeoPoint.getLatitude(), berlinWallMemorialGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(milanCathedralGeoPoint.getLatitude(), milanCathedralGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(galleriaVittorioEmanueleIIGeoPoint.getLatitude(), galleriaVittorioEmanueleIIGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(teatroAllaScalaGeoPoint.getLatitude(), teatroAllaScalaGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(sforzaCastleGeoPoint.getLatitude(), sforzaCastleGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(casaBatlloGeoPoint.getLatitude(), casaBatlloGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(laPedreraGeoPoint.getLatitude(), laPedreraGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(sagradaFamiliaGeoPoint.getLatitude(), sagradaFamiliaGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(parkGuellGeoPoint.getLatitude(), parkGuellGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(louvreMuseumGeoPoint.getLatitude(), louvreMuseumGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(tuileriesGardenGeoPoint.getLatitude(), tuileriesGardenGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(museeDOrsayGeoPoint.getLatitude(), museeDOrsayGeoPoint.getLongitude()));
            appDataBase.markOfSightseeingDao().insert(new MarkOfSightseeing(notreDameCathedralGeoPoint.getLatitude(), notreDameCathedralGeoPoint.getLongitude()));
        });
    }
}
