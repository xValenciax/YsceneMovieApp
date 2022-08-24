package com.example.ycseneapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserAccountActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView searchBtn, favBtn, accountBtn, homepageBtn, uploadImg, Profilepic;
    private EditText name, email, phone, pass, bio;
    private TextView userName;
    private AppCompatButton save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        db_User database = new db_User(UserAccountActivity.this);

        SharedPreferences prefs = getSharedPreferences("userId", MODE_PRIVATE);
        int id = prefs.getInt("id", -1);

        User currentUser = database.getUser(id);

        searchBtn = (ImageView) findViewById(R.id.searchbtn5);
        homepageBtn = (ImageView) findViewById(R.id.HomePage5);
        accountBtn = (ImageView) findViewById(R.id.account5);
        favBtn = (ImageView) findViewById(R.id.favorites5);
        uploadImg = (ImageView) findViewById(R.id.uploadImg);
        Profilepic = (ImageView) findViewById(R.id.Profilepic);

        bio = (EditText) findViewById(R.id.bio);
        name = (EditText) findViewById(R.id.name);
        userName = (TextView) findViewById(R.id.userName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.pass);
        save = (AppCompatButton) findViewById(R.id.save);

        bio.setText(currentUser.getBio());
        name.setText(currentUser.getName());
        userName.setText(currentUser.getName());
        email.setText(currentUser.getEmail());
        phone.setText(currentUser.getPhone_number());
        pass.setText(currentUser.getPassword());

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                String Pass = pass.getText().toString();
                String Bio = bio.getText().toString();
                // create a new user with entered data
                User user = new User(Name, Email, Phone, Pass);
                user.setBio(Bio);
                // open a database connection
                db_User database = new db_User(UserAccountActivity.this);
                // add user's data to database
                database.updateUser(user);
                Toast.makeText(UserAccountActivity.this, "Information Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccountActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        homepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccountActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccountActivity.this, UserAccountActivity.class);
                startActivity(intent);
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserAccountActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.d("Path", "onActivityResult: " + picturePath);
            Bitmap myBitmap = BitmapFactory.decodeFile(picturePath);
            Profilepic.setImageBitmap(myBitmap);
        }
    }
}