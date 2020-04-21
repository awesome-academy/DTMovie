package com.example.dtmovie.data.source;

import androidx.lifecycle.LiveData;

import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.reponse.GenresReponse;
import com.example.dtmovie.data.reponse.MovieReponse;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDataSource {
    interface Remote {
        Observable<MovieReponse> getMoviesByCategory(String category, int page);

        Observable<MovieReponse> getTrendingMovie();

        Observable<GenresReponse> getMovieGenres();

        Observable<Movie> getMovieDetail(int id);
    }

    interface Local {
        List<MovieCategory> getCategories();

        LiveData<List<Movie>> getAllMovies();

        LiveData<Movie> getMovieById(int movieId);

        void addFavoriteMovie(Movie movie);

        void deleteFavoriteMovie(Movie movie);

        boolean isFavorite(int movieId);
    }
}
