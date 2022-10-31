package com.example.onethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        Handler handler = new Handler();
        handler.postDelayed(
                new Runnable() {
                   @Override
                   public void run() {
                        startActivity(intent);
                    }
               }, 3000);

    }
}