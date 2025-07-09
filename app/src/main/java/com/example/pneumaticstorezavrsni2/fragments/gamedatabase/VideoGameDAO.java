package com.example.pneumaticstorezavrsni2.fragments.gamedatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface VideoGameDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(VideoGameDef game);

    @Query("SELECT * FROM games")
    List<VideoGameDef> getAllGames();

    @Query("SELECT * FROM games WHERE title = :title LIMIT 1")
    VideoGameDef findGameByTitle(String title);

    @Query("DELETE FROM games")
    void deleteAllGames();

    @Query("SELECT * FROM games WHERE isBought = 1")
    List<VideoGameDef> getBoughtGames();

    @Query("UPDATE games SET isBought = :bought WHERE title = :title")
    void updateBoughtStatus(String title, boolean bought);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<VideoGameDef> games);

}
