package com.example.dtmovie.data.responsitory;

import androidx.lifecycle.LiveData;

import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.reponse.GenresReponse;
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
    public Observable<Movie> getDetailMovie(int movieId) {
        return mMovieRemoteData.getDetailMovie(movieId);
    }

    @Override
    public Observable<MovieReponse> getSearchMovie(String key, String query) {
        return mMovieRemoteData.getSearchMovie(key, query);
    }

    @Override
    public List<MovieCategory> getCategories() {
        return mMovieLocalData.getCategories();
    }

    @Override
    public LiveData<List<Movie>> getAllMovies() {
        return mMovieLocalData.getAllMovies();
    }

    @Override
    public LiveData<Movie> getMovieById(int movieId) {
        return mMovieLocalData.getMovieById(movieId);
    }

    @Override
    public void addFavoriteMovie(Movie movie) {
        mMovieLocalData.addFavoriteMovie(movie);
    }

    @Override
    public void deleteFavoriteMovie(Movie movie) {
        mMovieLocalData.deleteFavoriteMovie(movie);
    }

    @Override
    public boolean isFavorite(int movieId) {
        return mMovieLocalData.isFavorite(movieId);
    }
}
