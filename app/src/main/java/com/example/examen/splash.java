package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(5000, 1000) {

            @Override
            public void onTick(long l) {

            }

            public void onFinish() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }.start();
    }
}