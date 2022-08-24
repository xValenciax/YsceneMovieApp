package com.example.ycseneapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TMDB_API {
    @GET("movie/{movie_id}")
    Call<Movie> fetchMovieById(
            @Path("movie_id") Integer id,
            @Query("api_key") String API_KEY
    );

    @GET("movie/top_rated")
    Call<SearchResults> fetchTopRatedMovies(
            @Query("api_key") String API_KEY,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("movie/popular")
    Call<SearchResults> fetchPopularMovies(
            @Query("api_key") String API_KEY,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("movie/{movie_id}/similar")
    Call<SearchResults> fetchSimilarMovies(
            @Query("api_key") String API_KEY,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("discover/movie")
    Call<SearchResults> fetchMoviesByYear(
            @Query("api_key") String API_KEY,
            @Query("year") Integer year,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("discover/movie")
    Call<SearchResults> fetchMoviesByGenre(
            @Query("api_key") String API_KEY,
            @Query("with_genres") String genres,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("discover/movie")
    Call<SearchResults> fetchMoviesByRating(
            @Query("api_key") String API_KEY,
            @Query("vote_average.lte") String rating,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("discover/movie")
    Call<SearchResults> searchForAMovie(
            @Query("api_key") String API_KEY,
            @Query("vote_average.gte") String rating,
            @Query("with_genres") String genres,
            @Query("year") Integer year,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );

    @GET("search/movie")
    Call<SearchResults> fetchMoviesByTitle(
            @Query("api_key") String API_KEY,
            @Query("query") String title,
            @Query("page") Integer pageNumber,
            @Query("include_adult") boolean adult
    );
}
