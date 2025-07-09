package com.example.pneumaticstorezavrsni2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pneumaticstorezavrsni2.GameDetailActivity;
import com.example.pneumaticstorezavrsni2.R;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDef;
import java.util.List;

public class GameCardAdapter extends RecyclerView.Adapter<GameCardAdapter.GameViewHolder> {

    private Context context;
    private List<VideoGameDef> gameList;

    public GameCardAdapter(Context context, List<VideoGameDef> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game_card, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        VideoGameDef game = gameList.get(position);
        holder.imageButton.setImageResource(game.getImageResId());
        holder.imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, GameDetailActivity.class);
            intent.putExtra("title", game.getTitle());
            intent.putExtra("description", game.getDescription());
            intent.putExtra("imageResId", game.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.gameImageButton);
        }
    }
}
