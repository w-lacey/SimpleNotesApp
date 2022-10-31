package com.example.onethingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.text.TextWatcher;

public class createAccount extends AppCompatActivity {

    TextView createAccountTitle;
    EditText createAccountEmail;
    EditText createAccountPassword;
    EditText confirmAccountPassword;
    Button confirmAccount;
    ImageView createAccountBanner;
    SQLConnection DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_account_layout);

        createAccountBanner = findViewById(R.id.createAccountBanner);
        createAccountEmail = findViewById(R.id.createAccountEmail);
        createAccountPassword = findViewById(R.id.createAccountPassword);
        confirmAccountPassword = findViewById(R.id.confirmAccountPassword);
        confirmAccount = findViewById(R.id.createAccountSubmit);

        DB = new SQLConnection(this);

        createAccountEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!createAccountEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    createAccountEmail.setError("Invalid Email Address");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        confirmAccountPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!createAccountPassword.getText().toString().equals(confirmAccountPassword.getText().toString())){
                    confirmAccountPassword.setError("Passwords do not match!");
                    confirmAccount.setEnabled(false);
                }else{
                    confirmAccount.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        confirmAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = DB.insertUserDate(createAccountEmail.getText().toString(), confirmAccountPassword.getText().toString());

                Intent intent = new Intent(createAccount.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}