package com.example.dtmovie.ui.home;

import androidx.databinding.ObservableField;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;

public class ItemMovieViewModel extends BaseViewModel {
    public ObservableField<Movie> mMovies = new ObservableField<>();

    public void setItemListNewsBiding(Movie movie) {
        mMovies.set(movie);
    }

    public Movie getNews() {
        return mMovies.get();
    }
}
