package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Converters {

    @TypeConverter
    public static String fromIdsListToStrings(List<Integer> listOfIds) {
        StringBuilder idString = new StringBuilder();
        for (Integer id : listOfIds) {
            if (idString.length() > 0) idString.append(",");
            idString.append(id);
        }
        return idString.toString();
    }

    @TypeConverter
    public static List<Integer> toIdsListFromStrings(String sightseeingIdString) {
        if (sightseeingIdString == null || sightseeingIdString.isEmpty()) {
            return new ArrayList<>();
        } else {
            List<String> stringList = Arrays.asList(sightseeingIdString.split(","));
            List<Integer> sightseeingIds = new ArrayList<>();
            for (String id : stringList) {
                sightseeingIds.add(Integer.parseInt(id));
            }
            return sightseeingIds;
        }
    }
}