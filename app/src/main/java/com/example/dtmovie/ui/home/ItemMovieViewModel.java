package com.example.dtmovie.ui.home;

import androidx.lifecycle.ViewModel;

import com.example.dtmovie.data.model.Movie;

public class ItemMovieViewModel extends ViewModel {
    private Movie mMovie;

    public ItemMovieViewModel() {
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
