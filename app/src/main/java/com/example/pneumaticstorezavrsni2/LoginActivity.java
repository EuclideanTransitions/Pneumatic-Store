package com.example.pneumaticstorezavrsni2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String correctEmail = "tester@pneumatic.com";
    private final String correctPassword = "password123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        EditText emailField = findViewById(R.id.emailInput);
        EditText passwordField = findViewById(R.id.passwordInput);
        Button loginBtn = findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();

            if (email.equals(correctEmail) && password.equals(correctPassword)) {
                // Save login state
                getSharedPreferences("AppPrefs", MODE_PRIVATE)
                        .edit().putBoolean("isLoggedIn", true).apply();

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
