package com.example.ycseneapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class db_Search_history extends SQLiteOpenHelper {
    private static String DB_NAME = "Project_DB";
    private static int DB_VERSION = 1;
    private static String TABLE_SEARCH_HISTORY = "search_history";
    private static String KEY_MOVIE_ID = "movie_id";
    private static String KEY_SEARCH_QUERY = "search_query";

    public db_Search_history(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_Table = "CREATE TABLE "+TABLE_SEARCH_HISTORY+"(id INTEGER PRIMARY KEY, movie_id INTEGER, search_query TEXT)";
        sqLiteDatabase.execSQL(create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String delete_Table = "DROP TABLE IF EXISTS "+TABLE_SEARCH_HISTORY;
        sqLiteDatabase.execSQL(delete_Table);

        onCreate(sqLiteDatabase);
    }

    public void AddSearchHistory(SearchHistory search){
        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_ID, search.getMovieId());
        values.put(KEY_SEARCH_QUERY, search.getSearchQuery());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SEARCH_HISTORY, null, values);
        db.close();
    }

    public ArrayList<SearchHistory> fetchSearchHistory(){
        ArrayList<SearchHistory> searchHistories = new ArrayList<SearchHistory>();
        String query = "SELECT * FROM "+TABLE_SEARCH_HISTORY;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int queryIndex = cursor.getColumnIndex(KEY_SEARCH_QUERY);
                int movieIdIndex = cursor.getColumnIndex(KEY_MOVIE_ID);
                String searchQuery = "";
                int movieId = 0;
                if(queryIndex > 0)
                    searchQuery = cursor.getString(queryIndex);
                if(movieIdIndex > 0)
                    movieId = cursor.getInt(movieIdIndex);
                SearchHistory search = new SearchHistory(movieIdIndex, searchQuery);
                searchHistories.add(search);
            }while(cursor.moveToNext());
        }
        db.close();
        return searchHistories;
    }
}
