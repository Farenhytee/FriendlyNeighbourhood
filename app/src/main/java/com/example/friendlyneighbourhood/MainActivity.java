package com.example.friendlyneighbourhood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button but_login, but_signup;
    String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        but_signup = findViewById(R.id.but_signup);
        but_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        but_login = findViewById(R.id.but_login);
        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        ConnectionHelper ch = new ConnectionHelper();

        username = etUsername.getText().toString();
        password = etPassword.getText().toString();

        if (ch.signin(username, password)) {
            Intent myIntent = new Intent(this, Home.class);
            startActivity(myIntent);
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void signup() {
        Intent myIntent = new Intent(this, Signup.class);
        startActivity(myIntent);
    }
}