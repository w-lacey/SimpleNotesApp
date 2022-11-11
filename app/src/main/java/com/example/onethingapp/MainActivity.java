package com.example.onethingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note_list);

    Boolean isFirstRun =
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);

    if (isFirstRun) {
      // show start activity
      startActivity(new Intent(MainActivity.this, SplashScreen.class));

    } else {
      startActivity(new Intent(MainActivity.this, NoteList.class));
    }
    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun", false).apply();
  }
}
