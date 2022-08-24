package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResultActivity extends AppCompatActivity{
    private GridView gridView;
    private String MovieTitle, searchYear, rating, withGenres;
    private String BASE_URL = "https://api.themoviedb.org/3/";
    private String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w185";
    private ImageView searchBtn, accountBtn, homepageBtn, favBtn , search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        MovieTitle = getIntent().getStringExtra("movieTitle");
        searchYear = getIntent().getStringExtra("searchYear");
        rating = getIntent().getStringExtra("rating");
        withGenres = getIntent().getStringExtra("withGenres");

        Log.d("test1", "onCreate: " + withGenres);


        gridView = (GridView) findViewById(R.id.viewAllGrid);

        if(!"".equals(MovieTitle) && (!"".equals(searchYear) || !"".equals(rating) || !"".equals(withGenres)))
            Toast.makeText(SearchResultActivity.this, "Please, Make sure to either provide a movie name or use other search options." +
                    "\nYou can't provide both", Toast.LENGTH_LONG);
        else if("".equals(MovieTitle))
            getSearchMovies(searchYear, rating, withGenres);
        else if(!"".equals(MovieTitle))
            getSearchMoviesByTitle();

        searchBtn = (ImageView) findViewById(R.id.Searchbtn4);
        homepageBtn = (ImageView) findViewById(R.id.HomePage4);
        accountBtn = (ImageView) findViewById(R.id.Account4);
        favBtn = (ImageView) findViewById(R.id.Favorites4);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        homepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, UserAccountActivity.class);
                startActivity(intent);
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void getSearchMoviesByTitle() {
        TMDB_API api = initializeRetrofit();
        Call<SearchResults> fetchedMovies = api.fetchMoviesByTitle(API_KEY, MovieTitle, 1, false);
        fetchedMovies.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                SearchResults searchedMovies = response.body();
                List<Movie> movies = searchedMovies.getMovies();
                List<Integer> ids = new ArrayList<Integer>();
                List<String> titles = new ArrayList<String>();
                List<Photo> posters = new ArrayList<Photo>();

                for (int i = 0; i < movies.size(); i++) {
                    if(i == movies.size())
                        break;
                    ids.add(movies.get(i).getId());
                    titles.add(movies.get(i).getTitle());
                    posters.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                }
                MovieViewAdapter movieViewAdapter = new MovieViewAdapter(SearchResultActivity.this, titles, posters);
                gridView.setAdapter(movieViewAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SearchResultActivity.this, MoviePageActivity.class);
                        intent.putExtra("movieId", movies.get(position).getId());
                        startActivity(intent);
                    }
                });
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
        Call<SearchResults> fetchedMovies = null;
        if(!"".equals(searchYear) && ("".equals(rating) && "".equals(withGenres))) {
            fetchedMovies = api.fetchMoviesByYear(API_KEY, Integer.valueOf(searchYear), 1, false);
        }
        else if(!"".equals(rating) && ("".equals(searchYear) && "".equals(withGenres)))
            fetchedMovies = api.fetchMoviesByRating(API_KEY, rating, 1, false);
        else if(!"".equals(withGenres) && ("".equals(rating) && "".equals(searchYear)))
            fetchedMovies = api.fetchMoviesByGenre(API_KEY, withGenres, 1, false);
        else if((!"".equals(searchYear) && !"".equals(rating)) && "".equals(withGenres))
            fetchedMovies = api.searchForAMovie(API_KEY, rating, null, Integer.valueOf(searchYear), 1, false);
        else if((!"".equals(searchYear) && !"".equals(withGenres)) && "".equals(rating))
            fetchedMovies = api.searchForAMovie(API_KEY, null, withGenres, Integer.valueOf(searchYear), 1, false);
        else if((!"".equals(rating) && !"".equals(withGenres)) && "".equals(searchYear))
            fetchedMovies = api.searchForAMovie(API_KEY, rating, withGenres, null, 1, false);

        fetchedMovies.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                SearchResults searchedMovies = response.body();
                List<Movie> movies = searchedMovies.getMovies();
                List<Integer> ids = new ArrayList<Integer>();
                List<String> titles = new ArrayList<String>();
                List<Photo> posters = new ArrayList<Photo>();

                for (int i = 0; i < movies.size(); i++) {
                    ids.add(movies.get(i).getId());
                    titles.add(movies.get(i).getTitle());
                    posters.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                }

                MovieViewAdapter movieViewAdapter = new MovieViewAdapter(SearchResultActivity.this, titles, posters);
                gridView.setAdapter(movieViewAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(SearchResultActivity.this, MoviePageActivity.class);
                        intent.putExtra("movieId", movies.get(position).getId());
                        startActivity(intent);
                    }
                });
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


