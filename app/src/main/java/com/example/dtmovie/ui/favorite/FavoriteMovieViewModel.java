package com.example.dtmovie.ui.favorite;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import java.util.List;

public class FavoriteMovieViewModel extends BaseViewModel {
    private MovieReponsitory mReponsitory;

    private LiveData<List<Movie>> mMovies;

    public void initViewModel(Context context) {
        mReponsitory = MovieReponsitory.getInstance(MovieRemoteData.getInstance(context)
                , MovieLocalData.getInstance(context));
        mMovies = mReponsitory.getAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return mMovies;
    }
}
