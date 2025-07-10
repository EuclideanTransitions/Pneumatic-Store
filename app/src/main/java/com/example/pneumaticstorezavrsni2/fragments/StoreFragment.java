package com.example.pneumaticstorezavrsni2.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pneumaticstorezavrsni2.R;
import com.example.pneumaticstorezavrsni2.adapters.GameCardAdapter;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDAO;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDatabase;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreFragment extends Fragment {

    private static final String TAG = "StoreFragment";

    // Four rows
    private RecyclerView row1Recycler, row2Recycler, row3Recycler, row4Recycler;
    private GameCardAdapter adapter1, adapter2, adapter3, adapter4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        // Set up RecyclerViews
        row1Recycler = view.findViewById(R.id.row1Recycler);
        row2Recycler = view.findViewById(R.id.row2Recycler);
        row3Recycler = view.findViewById(R.id.row3Recycler);
        row4Recycler = view.findViewById(R.id.row4Recycler);

        row1Recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        row2Recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        row3Recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        row4Recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        loadGamesAndPreloadIfEmpty();

        return view;
    }

    private void loadGamesAndPreloadIfEmpty() {
        new Thread(() -> {
            VideoGameDAO dao = VideoGameDatabase.getInstance(getContext()).videogameDAO();

            List<VideoGameDef> allGames = dao.getAllGames();

            if (dao.findGameByTitle("Partial-Life") == null) {
                dao.insertAll(Arrays.asList(
                        new VideoGameDef("Partial-Life", "Sci-fi shooter", R.drawable.partiallife),
                        new VideoGameDef("Partial-Life 2", "Sci-fi shooter", R.drawable.partiallife2),
                        new VideoGameDef("Left 2 Die", "Zombie shooter", R.drawable.left2die),
                        new VideoGameDef("Left 2 Die2", "Zombie shooter", R.drawable.left2die2),
                        new VideoGameDef("Gateway", "Puzzle", R.drawable.gateway),
                        new VideoGameDef("Gateway 2", "Puzzle", R.drawable.gateway2),
                        new VideoGameDef("Parry-Attack", "Competitive, Shooter", R.drawable.parry_attack),
                        new VideoGameDef("Team Base 2", "Team based, Shooter", R.drawable.team_base2),
                        new VideoGameDef("Stalemate", "Competitive, Team based, Shooter", R.drawable.stalemate)



                ));
                allGames = dao.getAllGames();
            }

// Categorize games
            List<VideoGameDef> topRated = new ArrayList<>();
            List<VideoGameDef> newReleases = new ArrayList<>();
            List<VideoGameDef> onSale = new ArrayList<>();
            List<VideoGameDef> recommended = new ArrayList<>();

            for (int i = 0; i < allGames.size(); i++) {
                switch (i % 4) {
                    case 0: topRated.add(allGames.get(i)); break;
                    case 1: newReleases.add(allGames.get(i)); break;
                    case 2: onSale.add(allGames.get(i)); break;
                    case 3: recommended.add(allGames.get(i)); break;
                }
            }

// Declare final copies for use inside lambda
            final List<VideoGameDef> finalTopRated = topRated;
            final List<VideoGameDef> finalNewReleases = newReleases;
            final List<VideoGameDef> finalOnSale = onSale;
            final List<VideoGameDef> finalRecommended = recommended;
            final int finalCount = allGames.size();

            requireActivity().runOnUiThread(() -> {
                adapter1 = new GameCardAdapter(getContext(), finalTopRated);
                adapter2 = new GameCardAdapter(getContext(), finalNewReleases);
                adapter3 = new GameCardAdapter(getContext(), finalOnSale);
                adapter4 = new GameCardAdapter(getContext(), finalRecommended);

                row1Recycler.setAdapter(adapter1);
                row2Recycler.setAdapter(adapter2);
                row3Recycler.setAdapter(adapter3);
                row4Recycler.setAdapter(adapter4);

                Log.d(TAG, "Rows populated: " + finalCount + " total games.");
            });
        }).start();
    }
}
