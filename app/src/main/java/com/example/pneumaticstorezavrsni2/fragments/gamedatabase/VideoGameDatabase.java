package com.example.pneumaticstorezavrsni2.fragments.gamedatabase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {VideoGameDef.class}, version = 6)
public abstract class VideoGameDatabase extends RoomDatabase{
    private static VideoGameDatabase instance;

    public abstract VideoGameDAO videogameDAO();

    public static synchronized VideoGameDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), VideoGameDatabase.class, "videogame_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
