package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePageActivity extends AppCompatActivity {
    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private int movieId;

    private TextView title, overview, rating, genre;
    private ImageView background, poster, searchBtn, homepageBtn, accountBtn, favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_page);
        title = (TextView) findViewById(R.id.movieName);
        overview = (TextView) findViewById(R.id.overview);
        rating = (TextView) findViewById(R.id.movieRating);
        genre = (TextView) findViewById(R.id.Genre);

        background = (ImageView) findViewById(R.id.blurryBackground);
        poster = (ImageView) findViewById(R.id.MoviePoster);
        movieId = getIntent().getIntExtra("movieId", 0);


        searchBtn = (ImageView) findViewById(R.id.Searchbtn1);
        homepageBtn = (ImageView) findViewById(R.id.HomePage1);
        accountBtn = (ImageView) findViewById(R.id.Account1);
        favBtn = (ImageView) findViewById(R.id.Favorites1);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoviePageActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        homepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoviePageActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoviePageActivity.this, UserAccountActivity.class);
                startActivity(intent);
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoviePageActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        GetMovie();
    }

    public TMDB_API initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDB_API tmdb_api = retrofit.create(TMDB_API.class);
        return tmdb_api;
    }

    public void GetMovie(){
        TMDB_API api = initializeRetrofit();

        Call<Movie> fetchedMovie = api.fetchMovieById(Integer.valueOf(movieId), API_KEY);

        fetchedMovie.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                List<Genre> genres = movie.getGenres();
                String genreText = "";
                Log.d("rating", "onResponse: "+movie.getRating());
                title.setText(movie.getTitle());
                overview.setText(movie.getOverview());
                rating.setText(movie.getRating().substring(0,3));
                Glide.with(MoviePageActivity.this).load(PIC_BASE_URL+movie.getPoster_path()).into(background);
                Glide.with(MoviePageActivity.this).load(PIC_BASE_URL+movie.getPoster_path()).into(poster);
                for(int i = 0; i < genres.size(); i++)
                    genreText += genres.get(i).getGenre() + "    ";
                genre.setText(genreText);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(MoviePageActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                Log.d("Errors", "FetchIds onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }
}