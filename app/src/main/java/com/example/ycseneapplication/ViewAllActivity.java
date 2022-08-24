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

public class ViewAllActivity extends AppCompatActivity {

    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w500";
    private GridView viewAll;
    private ImageView searchBtn, homepageBtn, accountBtn, favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        String SearchFor = getIntent().getStringExtra("SearchFor");

        viewAll = (GridView) findViewById(R.id.viewAllGrid);

        getMovies(SearchFor);

        searchBtn = (ImageView) findViewById(R.id.Searchbtn4);
        homepageBtn = (ImageView) findViewById(R.id.HomePage4);
        accountBtn = (ImageView) findViewById(R.id.Account4);
        favBtn = (ImageView) findViewById(R.id.Favorites4);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
        homepageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });
        accountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllActivity.this, UserAccountActivity.class);
                startActivity(intent);
            }
        });
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewAllActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });


    }

    public TMDB_API initializeRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TMDB_API tmdb_api = retrofit.create(TMDB_API.class);
        return tmdb_api;
    }

    public void getMovies(String SearchFor){
        if(SearchFor.equals("Top Rated")){
            TMDB_API api = initializeRetrofit();
            Call<SearchResults> TopRatedMovies = api.fetchTopRatedMovies(API_KEY, 1, false);

            TopRatedMovies.enqueue(new Callback<SearchResults>() {
                @Override
                public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                    SearchResults TopRated = response.body();
                    List<Movie> movies = TopRated.getMovies();
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
                    MovieViewAdapter movieViewAdapter = new MovieViewAdapter(ViewAllActivity.this, titles, posters);
                    viewAll.setAdapter(movieViewAdapter);
                    viewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ViewAllActivity.this, MoviePageActivity.class);
                            intent.putExtra("movieId", movies.get(position).getId());
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onFailure(Call<SearchResults> call, Throwable t) {
                    Toast.makeText(ViewAllActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                    Log.d("Errors", "FetchIds onFailure: " + t.getMessage());
                    t.printStackTrace();
                }
            });
        }
        if(SearchFor.equals("Popular")){
            TMDB_API api = initializeRetrofit();
            Call<SearchResults> TopRatedMovies = api.fetchPopularMovies(API_KEY, 1, false);

            TopRatedMovies.enqueue(new Callback<SearchResults>() {
                @Override
                public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                    SearchResults TopRated = response.body();
                    List<Movie> movies = TopRated.getMovies();
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
                    MovieViewAdapter movieViewAdapter = new MovieViewAdapter(ViewAllActivity.this, titles, posters);
                    viewAll.setAdapter(movieViewAdapter);
                    viewAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ViewAllActivity.this, MoviePageActivity.class);
                            intent.putExtra("movieId", movies.get(position).getId());
                            startActivity(intent);
                        }
                    });
                }

                @Override
                public void onFailure(Call<SearchResults> call, Throwable t) {
                    Toast.makeText(ViewAllActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                    Log.d("Errors", "FetchIds onFailure: " + t.getMessage());
                    t.printStackTrace();
                }
            });
        }
    }
}