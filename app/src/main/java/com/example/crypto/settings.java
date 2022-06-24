package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ImageView back = findViewById(R.id.back);
        RelativeLayout verify = findViewById(R.id.verify);
        RelativeLayout security = findViewById(R.id.security);
        RelativeLayout log = findViewById(R.id.logOut);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelperClass.clear(settings.this);
                startActivity(new Intent(settings.this, loginActivity.class));
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, verifyAccount.class);
                startActivity(i);
            }
        });

        security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settings.this, securityActivity.class);
                startActivity(i);
            }
        });
    }
}