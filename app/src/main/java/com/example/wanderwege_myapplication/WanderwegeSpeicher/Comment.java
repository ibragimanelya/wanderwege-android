package com.example.wanderwege_myapplication.WanderwegeSpeicher;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "comment")
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String comment;

    public Comment(){}

    @Ignore
    public Comment(String name, String comment) {
        setName(name);
        setComment(comment);
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }
}