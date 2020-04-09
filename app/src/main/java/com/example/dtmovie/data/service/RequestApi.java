package com.example.dtmovie.data.service;

import com.example.dtmovie.data.model.MovieReponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApi {
    @GET("movie/{category}")
    Observable<MovieReponse> getMovieByCategory(@Path("category") String category, @Query("page") int page);

    @GET("trending/all/day")
    Observable<MovieReponse> getMovieTrending();

}
