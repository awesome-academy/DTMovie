package com.example.dtmovie.ui.home;

import androidx.databinding.ObservableField;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.ui.home.adapter.ItemMovieListener;

public class ItemMovieViewModel extends BaseViewModel {
    public ObservableField<Movie> mMovies = new ObservableField<>();
    private ItemMovieListener mListener;

    public ItemMovieViewModel(ItemMovieListener listener) {
        mListener = listener;
    }

    public void setItemListNewsBiding(Movie movie) {
        mMovies.set(movie);
    }

    public Movie getNews() {
        return mMovies.get();
    }

    public void setListener(ItemMovieListener listener) {
        mListener = listener;
    }
}
