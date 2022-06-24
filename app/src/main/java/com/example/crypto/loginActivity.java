package com.example.crypto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.concurrent.Executor;

public class loginActivity extends AppCompatActivity {

    PageAdapter pageAdapter;
    BiometricPrompt biometricPrompt;
    BiometricPrompt.PromptInfo promptInfo;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout = findViewById(R.id.main_layout);

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL)){

            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(loginActivity.this, "Device doesn't have any fingerprint", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(loginActivity.this, "Not working", Toast.LENGTH_SHORT).show();
                break;

            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(loginActivity.this, "No Fingerprint assigned", Toast.LENGTH_SHORT).show();
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new androidx.biometric.BiometricPrompt(loginActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(loginActivity.this, "Authenticated", Toast.LENGTH_SHORT).show();
                if (userHelperClass.contains(loginActivity.this, "login")) {
                    startActivity(new Intent(loginActivity.this, homeScreen.class));
                    finish();
                } else {
                    layout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder().setTitle("The crypto app")
                .setDescription("Confirm your fingerprint lock to access the app")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL).build();

        biometricPrompt.authenticate(promptInfo);

        ViewPager viewPager = findViewById(R.id.view_pager);
        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() == 0 || tab.getPosition() == 1){
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    }
}