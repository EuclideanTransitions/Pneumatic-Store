package com.example.pneumaticstorezavrsni2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Handler;
import android.os.Looper;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDAO;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDatabase;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDef;
import androidx.fragment.app.Fragment;
import com.example.pneumaticstorezavrsni2.R;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pneumaticstorezavrsni2.adapters.GameCardAdapter;

public class LibraryFragment extends Fragment {

    private RecyclerView recyclerView;
    private GameCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        recyclerView = view.findViewById(R.id.libraryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadGames();

        return view;
    }

    private void loadGames() {
        VideoGameDatabase db = VideoGameDatabase.getInstance(getContext());
        VideoGameDAO dao = db.videogameDAO();

        new Thread(() -> {
            List<VideoGameDef> games = dao.getBoughtGames();

            new Handler(Looper.getMainLooper()).post(() -> {
                adapter = new GameCardAdapter(getContext(), games);
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
}
