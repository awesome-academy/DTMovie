package com.example.dtmovie.ui.home;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.ui.home.adapter.MovieAdapter;

public class ItemTrendingMovieViewModel extends ViewModel {
    private Movie mMovie;

    public ItemTrendingMovieViewModel() {
    }

    public Movie getMovie() {
        return mMovie;
    }

    public void setMovie(Movie movie) {
        mMovie = movie;
    }

    public String getTitle() {
        return mMovie.getTitle();
    }

    public String getBackdropPath() {
        return mMovie.getBackdropPath();
    }

    public String getReleaseDate() {
        return mMovie.getReleaseData();
    }

    public double getVoteAverage() {
        return mMovie.getVoteAverAge();
    }
}
