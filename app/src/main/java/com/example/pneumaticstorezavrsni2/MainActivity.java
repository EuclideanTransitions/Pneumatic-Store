package com.example.pneumaticstorezavrsni2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.pneumaticstorezavrsni2.fragments.*;

public class MainActivity extends AppCompatActivity {

    TextView topTitle;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check login status BEFORE setting content view
        boolean isLoggedIn = getSharedPreferences("AppPrefs", MODE_PRIVATE)
                .getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        // Only proceed if logged in
        setContentView(R.layout.activity_main);

        topTitle = findViewById(R.id.top_title);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StoreFragment())
                .commit();
        topTitle.setText("Store");

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_store) {
                selectedFragment = new StoreFragment();
                topTitle.setText("Store");
            } else if (itemId == R.id.nav_library) {
                selectedFragment = new LibraryFragment();
                topTitle.setText("Library");
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
                topTitle.setText("Profile");
            } else if (itemId == R.id.nav_settings) {
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
