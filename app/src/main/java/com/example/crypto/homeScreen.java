package com.example.crypto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeScreen extends AppCompatActivity {

    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.exchange);
        ImageView settings = findViewById(R.id.settings);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new exchangeFragment()).commit();
        }

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeScreen.this, com.example.crypto.settings.class);
                startActivity(i);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                if (item.getItemId() == R.id.home) {
                    fragment = new homeFragment();
                } else if (item.getItemId() == R.id.exchange) {
                    fragment = new exchangeFragment();

                } else if (item.getItemId() == R.id.orders) {
                    fragment = new orderFragment();

                } else if (item.getItemId() == R.id.funds) {
                    fragment = new fundsFragment();
                }

                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment)
                        .commit();
                return true;
            }
        });
    }
}