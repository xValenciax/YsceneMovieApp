package com.example.ycseneapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageActivity extends AppCompatActivity {

    private final String BASE_URL = "https://api.themoviedb.org/3/";
    private final String API_KEY = "cfd7f3ce6354b731591f4e5535a970cd";
    private final String PIC_BASE_URL = "http://image.tmdb.org/t/p/w500";
    SliderView sliderView, sliderView2;
    ImageView searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getTopRatedListPhoto();
        getPopularListPhoto();
        searchBtn = (ImageView) findViewById(R.id.imageView9);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, SearchPageActivity.class);
                startActivity(intent);
            }
        });
    }



    public void getPopularListPhoto(){
        TMDB_API api = initializeRetrofit();
        Call<SearchResults> fetchIds = api.fetchPopularMovies(API_KEY, 1);

        fetchIds.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.isSuccessful()){
                    SearchResults searchResults = response.body();
                    List<Movie> movies = searchResults.getMovies();
                    List<Photo> moviesPhotos = new ArrayList<Photo>();
                    for(int i = 0; i < 5; i++) {
                        moviesPhotos.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                        Log.d("TAGGG", "onResponse: " + PIC_BASE_URL + movies.get(i).getPoster_path());
                    }


                    sliderView2 = (SliderView) findViewById(R.id.image_slider2);

                    SliderAdapter sliderAdapter2 = new SliderAdapter(HomePageActivity.this, moviesPhotos);
                    sliderView2.setSliderAdapter(sliderAdapter2);
                    sliderView2.setIndicatorAnimation(IndicatorAnimationType.DROP);
                    sliderView2.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                    sliderView2.startAutoCycle();
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Toast.makeText(HomePageActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                Log.d("Errors", "FetchIds onFailure: " + t.getMessage() + " " + t.getStackTrace());
            }
        });
    }

    public void getTopRatedListPhoto(){
        TMDB_API api = initializeRetrofit();
        Call<SearchResults> fetchIds = api.fetchTopRatedMovies(API_KEY, 1);

        fetchIds.enqueue(new Callback<SearchResults>() {
            @Override
            public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                if(response.isSuccessful()){
                    SearchResults searchResults = response.body();
                    List<Movie> movies = searchResults.getMovies();
                    List<Photo> moviesPhotos = new ArrayList<Photo>();
                    for(int i = 0; i < 5; i++) {
                        moviesPhotos.add(new Photo(PIC_BASE_URL + movies.get(i).getPoster_path()));
                    }

                    sliderView = (SliderView) findViewById(R.id.image_slider);

                    SliderAdapter sliderAdapter = new SliderAdapter(HomePageActivity.this, moviesPhotos);
                    sliderView.setSliderAdapter(sliderAdapter);
                    sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
                    sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
                    sliderView.startAutoCycle();
                }
            }

            @Override
            public void onFailure(Call<SearchResults> call, Throwable t) {
                Toast.makeText(HomePageActivity.this, "There's an internal error", Toast.LENGTH_SHORT);
                Log.d("Errors", "FetchIds onFailure: " + t.getMessage() + " " + t.getStackTrace());
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
}