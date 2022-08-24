package com.example.ycseneapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    ImageView signUp;
    ImageView Login;
    TextView email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUp = (ImageView) findViewById(R.id.imageView8);
        Login = (ImageView) findViewById(R.id.imageView10);
        email = findViewById(R.id.editTextTextEmailAddress2);
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
                final User[] users = new User[1];
                int userId = -1;
                String pass;
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(LoginActivity.this,"EMPTY DATA PROVIDED", Toast.LENGTH_SHORT).show();
                    return;
                }

                db_User database = new db_User(LoginActivity.this);
                userId = database.GetUserByEmail(email.getText().toString());
                //send user id for other activities
                SharedPreferences.Editor editor = getSharedPreferences("userId", MODE_PRIVATE).edit();
                editor.putInt("id", userId);
                editor.apply();
                users[0] = database.getUser(userId);
                Log.d("Userr", "onClick: " + users[0].getPassword());
                if(password.getText().toString().equals(users[0].getPassword()))
                {
                    Log.d("TAG1", "onClick: Done signing in");
                    //Correct
                    Toast.makeText(LoginActivity.this,"LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);

                    startActivity(intent);
                }
                else if(! password.getText().toString().equals(users[0].getPassword()))
                    //Incorrect
                    Toast.makeText(LoginActivity.this,"Invalid Email or Password", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(LoginActivity.this,"This User Doesn't Exist", Toast.LENGTH_SHORT).show();
            }
        });

    }
}