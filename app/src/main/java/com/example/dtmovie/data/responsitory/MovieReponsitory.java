package com.example.dtmovie.data.responsitory;

import com.example.dtmovie.data.model.MovieReponse;
import com.example.dtmovie.data.source.MovieDataSource;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import io.reactivex.Observable;

public class MovieReponsitory implements MovieDataSource.remote {
    private static MovieReponsitory sMovieReponsitory;
    private MovieRemoteData mMovieRemoteData;

    private MovieReponsitory(MovieRemoteData movieRemoteData) {
        mMovieRemoteData = movieRemoteData;
    }

    public static MovieReponsitory getInstance(MovieRemoteData remote) {
        if (sMovieReponsitory == null) {
            sMovieReponsitory = new MovieReponsitory(remote);
        }
        return sMovieReponsitory;
    }

    @Override
    public Observable<MovieReponse> getMoviesByCategory(String category, int page) {
        return mMovieRemoteData.getMoviesByCategory(category, page);
    }
}
