package com.example.pneumaticstorezavrsni2.fragments.gamedatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class VideoGameDef {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private int imageResId;

    private boolean isBought;

    public VideoGameDef(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.isBought = false; // default not bought
    }

    // Getter and setter for id (required by Room)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public boolean isBought() {
        return isBought;
    }
    public void setBought(boolean bought) {
        isBought = bought;
    }
}
