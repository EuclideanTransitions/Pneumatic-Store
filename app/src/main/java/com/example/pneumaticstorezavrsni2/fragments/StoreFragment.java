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
import java.util.List;
import java.util.Arrays;

public class StoreFragment extends Fragment {

    private static final String TAG = "StoreFragment";

    private RecyclerView recyclerView;
    private GameCardAdapter adapter;
    private List<VideoGameDef> gamesList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        recyclerView = view.findViewById(R.id.row1Recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new GameCardAdapter(getContext(), gamesList);
        recyclerView.setAdapter(adapter);

        Log.d(TAG, "Adapter set on RecyclerView: " + (recyclerView.getAdapter() != null));

        loadGamesAndPreloadIfEmpty();

        return view;
    }

    private void loadGamesAndPreloadIfEmpty() {
        new Thread(() -> {
            VideoGameDAO dao = VideoGameDatabase.getInstance(getContext()).videogameDAO();

            List<VideoGameDef> currentGames = dao.getAllGames();

            if (dao.findGameByTitle("Partial-Life") == null) {
                dao.insertAll(Arrays.asList(
                        new VideoGameDef("Partial-Life", "Sci-fi shooter", R.drawable.partiallife),
                        new VideoGameDef("Partial-Life 2", "Sci-fi shooter", R.drawable.partiallife2),
                        new VideoGameDef("Left 2 Die", "Zombie shooter", R.drawable.left2die),
                        new VideoGameDef("Left 2 Die2", "Zombie shooter", R.drawable.left2die2)
                ));
                currentGames = dao.getAllGames();  // reload after insert
            } else {
                Log.d(TAG, "Games already present in DB, skipping insert");
            }


            List<VideoGameDef> finalCurrentGames = currentGames;
            requireActivity().runOnUiThread(() -> {
                gamesList.clear();
                gamesList.addAll(finalCurrentGames);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Updated UI with " + finalCurrentGames.size() + " games");
            });
        }).start();
    }
}
