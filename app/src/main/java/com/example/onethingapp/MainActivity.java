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

    TextView loginPrompt;
    EditText emailInput;
    EditText passwordInput;
    Button loginSubmit;
    TextView createAccountLink;
    SQLConnection DB;
    private SharedPreferences sharedpreferences;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView logoBanner = findViewById(R.id.logoBanner);
        EditText emailInput = findViewById(R.id.inputEmail);
        EditText passwordInput = findViewById(R.id.inputPassword);
        Button loginSubmit = findViewById(R.id.loginSubmitButton);
        Button createAccountLink = findViewById(R.id.createAccountLink);
        DB = new SQLConnection(this);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (isFirstRun) {
            //show start activity
            startActivity(new Intent(MainActivity.this, SplashScreen.class));

        }


        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                boolean check = DB.validateLogin(email, password);
                if(check){
                    Intent intent = new Intent(MainActivity.this, EnterNote.class);
                    startActivity(intent);
                }else{
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this).setTitle(" NOT SUCCESS").setMessage("YOU DONT EXISTS");
                    alertDialog.show();
                }
            }
        });
        createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
                finish();
            }
        });
    }
}