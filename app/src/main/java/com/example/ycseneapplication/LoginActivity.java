package com.example.ycseneapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    ImageView signUp;
    ImageView Login;
    TextView username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = (ImageView) findViewById(R.id.imageView8);
        Login = (ImageView) findViewById(R.id.imageView10);
        username = findViewById(R.id.editTextTextEmailAddress2);
        password = findViewById(R.id.editTextTextPassword);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"EMPTY DATA PROVIDED", Toast.LENGTH_LONG).show();
                }
                else if(username.getText().toString().equals(set_username) && password.getText().toString().equals(set_password))
                {
                    //Correct
                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
                else
                    //Incorrect
                    Toast.makeText(LoginActivity.this,"LOGIN FAILED", Toast.LENGTH_SHORT).show();
            }
        });

    }
}