package com.example.onethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
  Animation animation;
  ImageView img;
    Handler handler = new Handler();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    img = (ImageView) findViewById(R.id.splashScreenLogo);
    animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop);
    img.startAnimation(animation);
    Intent intent = new Intent(SplashScreen.this, MainActivity.class);

    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            startActivity(intent);
          }
        },
        3000);
  }
}
