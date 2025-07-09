package com.example.pneumaticstorezavrsni2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.pneumaticstorezavrsni2.fragments.*;
import com.example.pneumaticstorezavrsni2.R;

public class MainActivity extends AppCompatActivity {

    TextView topTitle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topTitle = findViewById(R.id.top_title);   //Top title
        bottomNavigationView = findViewById(R.id.bottom_navigation);   // Bot nav

        // Set default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StoreFragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();

            if (itemId == R.id.nav_store) {
                selectedFragment = new StoreFragment();
                topTitle.setText("Store");
            }

            else if (itemId == R.id.nav_library) {
                selectedFragment = new LibraryFragment();
                topTitle.setText("Library");
            }

            else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
                topTitle.setText("Profile");
            }

            else if (itemId == R.id.nav_settings) {
                selectedFragment = new SettingsFragment();
                topTitle.setText("Settings");
            }


            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }
}