package com.example.pneumaticstorezavrsni2.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.pneumaticstorezavrsni2.LoginActivity;
import com.example.pneumaticstorezavrsni2.FriendsActivity;
import com.example.pneumaticstorezavrsni2.GroupsActivity;
import com.example.pneumaticstorezavrsni2.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            // Clear login status
            SharedPreferences prefs = requireActivity().getSharedPreferences("AppPrefs", getContext().MODE_PRIVATE);
            prefs.edit().putBoolean("isLoggedIn", false).apply();

            // Go to login screen
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish();
        });

        // Go to friends list
        Button friendsButton = view.findViewById(R.id.friends_button);
        friendsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FriendsActivity.class);
            startActivity(intent);
        });

        // Go to groups list
        Button groupsButton = view.findViewById(R.id.groups_button);
        groupsButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), GroupsActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
