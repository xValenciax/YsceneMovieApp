package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class SearchPageActivity extends AppCompatActivity {
    private final String BASE_SEARCH_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w500";

    private String withGenres = "", ratingVal = "", yearVal = "", movieVal = "";

    private EditText rating, Year, Movie;

    private AppCompatButton search;

    private CheckBox[] checkBoxes;
    private final Genre[] genres = {
            new Genre(28, "Action"),
            new Genre(27, "Horror"),
            new Genre(35, "Comedy"),
            new Genre(878, "Science Fiction"),
            new Genre(18, "Drama"),
            new Genre(99, "Documentary"),
            new Genre(10752, "War"),
            new Genre(10749, "Romance"),
            new Genre(16, "Animation"),
            new Genre(10751, "Family"),
            new Genre(80, "Crime"),
            new Genre(9648, "Mystery")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);

        checkBoxes = new CheckBox[]{
                (CheckBox) findViewById(R.id.radioButton),
                (CheckBox) findViewById(R.id.radioButton2),
                (CheckBox) findViewById(R.id.radioButton3),
                (CheckBox) findViewById(R.id.radioButton4),
                (CheckBox) findViewById(R.id.radioButton5),
                (CheckBox) findViewById(R.id.radioButton6),
                (CheckBox) findViewById(R.id.radioButton7),
                (CheckBox) findViewById(R.id.radioButton8),
                (CheckBox) findViewById(R.id.radioButton9),
                (CheckBox) findViewById(R.id.radioButton10),
                (CheckBox) findViewById(R.id.radioButton11),
                (CheckBox) findViewById(R.id.radioButton12)
        };

        rating = (EditText) findViewById(R.id.rating);
        Year = (EditText) findViewById(R.id.Year);
        Movie = (EditText) findViewById(R.id.Movie);
        search = (AppCompatButton) findViewById(R.id.search);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCheckedGenres();

                ratingVal= rating.getText().toString();
                yearVal = Year.getText().toString();
                movieVal = Movie.getText().toString();

                Intent intent = new Intent(SearchPageActivity.this, SearchResultActivity.class);
                intent.putExtra("movieTitle", movieVal);
                intent.putExtra("searchYear", yearVal);
                intent.putExtra("rating", ratingVal);
                intent.putExtra("withGenres", withGenres);
                startActivity(intent);
            }
        });

    }
    private void getCheckedGenres(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 12; i++)
                    if(checkBoxes[i].isChecked())
                        withGenres += genres[i].getGenre() + ",";
            }
        }).start();
    }
}