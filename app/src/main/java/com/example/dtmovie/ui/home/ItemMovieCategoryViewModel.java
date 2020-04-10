package com.example.dtmovie.ui.home;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;

public class ItemMovieCategoryViewModel extends BaseViewModel {
    public ObservableField<Movie> mMovies = new ObservableField<>();
}
