package com.example.dtmovie.data.source.local;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.dtmovie.constant.CategoryKey;
import com.example.dtmovie.constant.CategoryName;
import com.example.dtmovie.data.database.MovieDao;
import com.example.dtmovie.data.database.MovieDataBase;
import com.example.dtmovie.data.database.asyntask.DeleteMovieAsynTask;
import com.example.dtmovie.data.database.asyntask.InsertMovieAsynTask;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.source.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

public class MovieLocalData implements MovieDataSource.Local {
    private static MovieLocalData sInstance;
    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mFavoritesMovies;

    public MovieLocalData(Context context) {
        MovieDataBase movieDataBase = MovieDataBase.getInstance(context);
        mMovieDao = movieDataBase.MovieDao();
        mFavoritesMovies = mMovieDao.getAllMovies();
    }

    public static MovieLocalData getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MovieLocalData(context);
        }
        return sInstance;
    }

    @Override
    public List<MovieCategory> getCategories() {
        List<MovieCategory> movieCategories = new ArrayList<>();
        MovieCategory now_playing = new MovieCategory(CategoryName.TITLE_NOW_PLAYING
                , CategoryKey.CATEGORY_NOW_PLAYING);
        MovieCategory popular = new MovieCategory(CategoryName.TITLE_POPULAR
                , CategoryKey.CATEGORY_POPULAR);
        MovieCategory top_rated = new MovieCategory(CategoryName.TITLE_TOP_RATE
                , CategoryKey.CATEGORY_TOP_RATE);
        MovieCategory upcoming = new MovieCategory(CategoryName.TITLE_UP_COMING
                , CategoryKey.CATEGORY_UP_COMING);
        movieCategories.add(now_playing);
        movieCategories.add(popular);
        movieCategories.add(top_rated);
        movieCategories.add(upcoming);
        return movieCategories;
    }

    @Override
    public LiveData<List<Movie>> getAllMovies() {
        return mFavoritesMovies;
    }

    @Override
    public LiveData<Movie> getMovieById(int movieId) {
        return mMovieDao.getMovieById(movieId);
    }

    @Override
    public void addFavoriteMovie(Movie movie) {
        new InsertMovieAsynTask(mMovieDao).execute(movie);
    }

    @Override
    public void deleteFavoriteMovie(Movie movie) {
        new DeleteMovieAsynTask(mMovieDao).execute(movie);
    }

    @Override
    public boolean isFavorite(int idMovie) {
        LiveData<Movie> favorite = getMovieById(idMovie);
        return favorite != null;
    }
}
