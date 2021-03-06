package com.example.dtmovie.data.service;

import com.example.dtmovie.BuildConfig;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.reponse.GenresReponse;
import com.example.dtmovie.data.reponse.MovieReponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApi {
    @GET("movie/{category}")
    Observable<MovieReponse> getMovieByCategory(@Path("category")
                                                        String category, @Query("page") int page);

    @GET("trending/all/day")
    Observable<MovieReponse> getTrendingMovie();

    @GET("genre/movie/list")
    Observable<GenresReponse> getGenresMovie();

    @GET("movie/{movie_id}?append_to_response=credits,videos,similar")
    Observable<Movie> getDetailMovie(@Path("movie_id") int id);

    @GET("search/movie")
    Observable<MovieReponse> getSearchMovie(@Query("api_key") String key, @Query("query") String query);
}
