package com.example.pneumaticstorezavrsni2;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDatabase;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDef;
import com.example.pneumaticstorezavrsni2.fragments.gamedatabase.VideoGameDAO;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class GameDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        TextView titleView = findViewById(R.id.detailTitle);
        TextView descView = findViewById(R.id.detailDesc);
        Button buyButton = findViewById(R.id.buyButton);
        Button backButton = findViewById(R.id.backButton);

        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        int imageResId = getIntent().getIntExtra("imageResId", R.drawable.partiallife);

        titleView.setText(title);
        descView.setText(desc);

        buyButton.setOnClickListener(v -> {
            final GameDetailActivity activity = this;

            new Thread(() -> {
                VideoGameDatabase db = VideoGameDatabase.getInstance(activity);
                VideoGameDAO dao = db.videogameDAO();

                VideoGameDef existing = dao.findGameByTitle(title);

                if (existing == null) {
                    // Insert new game and mark it as bought
                    VideoGameDef game = new VideoGameDef(title, desc, imageResId);
                    game.setBought(true);  // mark as bought
                    dao.insert(game);

                    activity.runOnUiThread(() ->
                            Toast.makeText(activity, "Added to Library", Toast.LENGTH_SHORT).show()
                    );
                } else if (!existing.isBought()) {
                    // If game exists but not bought, update bought status
                    dao.updateBoughtStatus(title, true);

                    activity.runOnUiThread(() ->
                            Toast.makeText(activity, "Marked as bought", Toast.LENGTH_SHORT).show()
                    );
                } else {
                    // Already bought
                    activity.runOnUiThread(() ->
                            Toast.makeText(activity, "Game already in Library", Toast.LENGTH_SHORT).show()
                    );
                }
            }).start();
        });



        backButton.setOnClickListener(v -> finish());
    }
}
