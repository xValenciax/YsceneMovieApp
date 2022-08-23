package com.example.ycseneapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class db_User extends SQLiteOpenHelper {

    private static String DB_NAME = "Project_DB";
    private static int DB_VERSION = 1;
    private static String TABLE_USERS = "users";
    private static String KEY_NAME = "name";
    private static String KEY_EMAIL = "email";
    private static String KEY_PHONE_NUMBER = "phone_number";
    private static String KEY_BIO = "bio";
    private static String KEY_PASSWORD = "password";

    public db_User(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase sqLiteDatabase) {
        String Create_Table = "CREATE TABLE users(id INTEGER PRIMARY KEY, name VARCHAR(25) NOT NULL, email VARCHAR(100) NOT NULL," +
                "phone_number TEXT UNIQUE, bio VARCHAR(200), password TEXT NOT NULL)";
        sqLiteDatabase.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String delete = "DROP TABLE IF EXISTS users";
        sqLiteDatabase.execSQL(delete);

        onCreate(sqLiteDatabase);
    }

    public void AddUser(User user){
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE_NUMBER, user.getPhone_number());
        values.put(KEY_BIO, user.getDescription());
        values.put(KEY_PASSWORD, user.getPassword());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public User getUser(int id){
        String query = "SELECT * FROM users WHERE id=?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        int name_index, email_index, phone_number_index, description_index, password_index;
        if(cursor != null){
            cursor.moveToFirst();
            name_index = cursor.getColumnIndex(KEY_NAME);
            email_index = cursor.getColumnIndex(KEY_EMAIL);
            phone_number_index = cursor.getColumnIndex(KEY_PHONE_NUMBER);
            description_index = cursor.getColumnIndex(KEY_BIO);
            password_index = cursor.getColumnIndex(KEY_PASSWORD);
            if(name_index >= 0 && email_index > 0 && phone_number_index > 0 && description_index > 0 && password_index > 0) {
                User Selected_user = new User();
                Selected_user.setName(cursor.getString(name_index));
                Selected_user.setEmail(cursor.getString(email_index));
                Selected_user.setPhone_number(cursor.getString(phone_number_index));
                Selected_user.setDescription(cursor.getString(description_index));
                Selected_user.setPassword(cursor.getString(password_index));
                return Selected_user;
            }
        }
        db.close();
        return null;
    }

    public String GetUserByEmail(String Email){
        String query = "SELECT password FROM users WHERE email=?";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{Email});
        int password_index;
        if(cursor != null){
            cursor.moveToFirst();
            password_index = cursor.getColumnIndex(KEY_PASSWORD);
            if(password_index > 0) {
                String passwordReturned = cursor.getString(password_index);
                return passwordReturned;
            }
        }
        db.close();
        return null;
    }

    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PHONE_NUMBER, user.getPhone_number());
        values.put(KEY_BIO, user.getDescription());
        values.put(KEY_PASSWORD, user.getPassword());

        db.update(TABLE_USERS, values, "id=?", new String[]{String.valueOf(user.getId())});

        db.close();
    }
    public void deleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_USERS, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
