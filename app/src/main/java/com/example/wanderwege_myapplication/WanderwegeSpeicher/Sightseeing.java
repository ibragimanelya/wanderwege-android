package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "sightseeing"/*,*/
//        foreignKeys = @ForeignKey(entity = MarkOfSightseeing.class,
//                parentColumns = "id",
//                childColumns = "mark_id"),
//indices = {@Index(value =  {"mark_id"})}
)
public class Sightseeing {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String year;
    private String history;

    //    @ColumnInfo(name = "mark_id")
//    private int markId;
    private String imageIdentifier;

    public Sightseeing(){}

    // Constructor, getters, and setters
    @Ignore
    public Sightseeing(String name, String year, String history, int markId, String imageIdentifier) {
        setName(name);
        setYear(year);
        setHistory(history);
        setMarkId(markId);
        this.imageIdentifier = imageIdentifier;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getImageIdentifier() {
        return imageIdentifier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageIdentifier(String imageIdentifier) {
        this.imageIdentifier = imageIdentifier;
    }

//    public int getMarkId() {
////        return markId;
//    }

    public void setMarkId(int markId) {
//        this.markId = markId;
    }

}