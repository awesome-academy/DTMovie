package com.example.dtmovie.data.source.remote;

import android.content.Context;

import com.example.dtmovie.data.reponse.GenresReponse;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.reponse.MovieReponse;
import com.example.dtmovie.data.service.NetworkService;
import com.example.dtmovie.data.service.RequestApi;
import com.example.dtmovie.data.source.MovieDataSource;

import io.reactivex.Observable;

public class MovieRemoteData implements MovieDataSource.Remote {
    private static MovieRemoteData sMovieRemoteData;
    private RequestApi mApi;

    private MovieRemoteData(RequestApi api) {
        mApi = api;
    }

    public static MovieRemoteData getInstance(Context context) {
        if (sMovieRemoteData == null) {
            sMovieRemoteData = new MovieRemoteData(NetworkService.getInstance(context));
        }
        return sMovieRemoteData;
    }

    @Override
    public Observable<MovieReponse> getMoviesByCategory(String category, int page) {
        return mApi.getMovieByCategory(category, page);
    }

    @Override
    public Observable<MovieReponse> getTrendingMovie() {
        return mApi.getTrendingMovie();
    }

    @Override
    public Observable<GenresReponse> getMovieGenres() {
        return mApi.getGenresMovie();
    }

    @Override
    public Observable<Movie> getDetailMovie(int id) {
        return mApi.getDetailMovie(id);
    }

    @Override
    public Observable<MovieReponse> getSearchMovie(String key, String query) {
        return mApi.getSearchMovie(key,query);
    }
}
