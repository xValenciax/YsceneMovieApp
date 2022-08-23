package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String MovieTitle, searchYear, rating, withGenres;
    private String BASE_URL = "https://api.themoviedb.org/3/";
    private String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        MovieTitle = getIntent().getStringExtra("movieTitle");
        searchYear = getIntent().getStringExtra("searchYear");
        rating = getIntent().getStringExtra("rating");
        withGenres = getIntent().getStringExtra("withGenres");


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        if(MovieTitle != "" && (searchYear != "" || rating != "" || withGenres != ""))
            Toast.makeText(SearchResultActivity.this, "Please, Make sure to either provide a movie name or use other search options." +
                    "\nYou can't provide both", Toast.LENGTH_LONG);

        else if (MovieTitle != null && MovieTitle != "")
            getSearchMoviesByTitle();

        else if(searchYear != "" || rating != "" || withGenres != ""){

        }

    }

    public void getSearchMoviesByTitle() {
        TMDB_API api = initializeRetrofit();
        Call<SearchResults> fetchedMovies = api.fetchMoviesByTitle(API_KEY, MovieTitle, 1);
        fetchedMovies.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                SearchResults searchedMovies = response.body();
                List<Movie> movies = searchedMovies.getMovies();
                List<String> titles = new ArrayList<String>();
                List<Photo> posters = new ArrayList<Photo>();

                for (int i = 0; i < 15; i++) {
                    titles.add(movies.get(i).getTitle());
                    posters.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                }

                MovieViewAdapter movieViewAdapter = new MovieViewAdapter(SearchResultActivity.this, titles, posters);
                recyclerView.setAdapter(movieViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(SearchResultActivity.this, DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                Log.d("Errors", "FetchIds onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public void getSearchMovies(String searchYear, String rating, String withGenres){
        TMDB_API api = initializeRetrofit();
        Call<SearchResults> fetchedMovies = api.searchForAMovie(API_KEY, rating, withGenres, Integer.valueOf(searchYear), 1);
        fetchedMovies.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                SearchResults searchedMovies = response.body();
                List<Movie> movies = searchedMovies.getMovies();
                List<String> titles = new ArrayList<String>();
                List<Photo> posters = new ArrayList<Photo>();

                for (int i = 0; i < 15; i++) {
                    titles.add(movies.get(i).getTitle());
                    posters.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                }
                
                MovieViewAdapter movieViewAdapter = new MovieViewAdapter(SearchResultActivity.this, titles, posters);
                recyclerView.setAdapter(movieViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SearchResultActivity.this));
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(SearchResultActivity.this, DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Toast.makeText(SearchResultActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                Log.d("Errors", "FetchIds onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    public TMDB_API initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDB_API tmdb_api = retrofit.create(TMDB_API.class);
        return tmdb_api;
    }
}


