package com.example.dtmovie.data.responsitory;

import com.example.dtmovie.data.reponse.GenresReponse;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.reponse.MovieReponse;
import com.example.dtmovie.data.source.MovieDataSource;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import java.util.List;

import io.reactivex.Observable;

public class MovieReponsitory implements MovieDataSource.Remote, MovieDataSource.Local {
    private static MovieReponsitory sMovieReponsitory;
    private MovieRemoteData mMovieRemoteData;
    private MovieLocalData mMovieLocalData;

    private MovieReponsitory(MovieRemoteData movieRemoteData
            , MovieLocalData movieLocalData) {
        mMovieRemoteData = movieRemoteData;
        mMovieLocalData = movieLocalData;
    }

    public static MovieReponsitory getInstance(MovieRemoteData remote, MovieLocalData local) {
        if (sMovieReponsitory == null) {
            sMovieReponsitory = new MovieReponsitory(remote, local);
        }
        return sMovieReponsitory;
    }

    @Override
    public Observable<MovieReponse> getMoviesByCategory(String category, int page) {
        return mMovieRemoteData.getMoviesByCategory(category, page);
    }

    @Override
    public Observable<MovieReponse> getTrendingMovie() {
        return mMovieRemoteData.getTrendingMovie();
    }

    @Override
    public Observable<GenresReponse> getMovieGenres() {
        return mMovieRemoteData.getMovieGenres();
    }

    @Override
    public Observable<Movie> getMovieDetail(int id) {
        return mMovieRemoteData.getMovieDetail(id);
    }

    @Override
    public List<MovieCategory> getCategories() {
        return mMovieLocalData.getCategories();
    }
}
