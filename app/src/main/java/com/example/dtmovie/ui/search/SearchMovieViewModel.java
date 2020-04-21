package com.example.dtmovie.ui.search;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dtmovie.BuildConfig;
import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchMovieViewModel extends BaseViewModel {
    private Context mContext;
    private CompositeDisposable mDisposable;
    private MovieReponsitory mReponsitory;

    public void initViewModel(Context context) {
        mContext = context;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.getInstance(MovieRemoteData.getInstance(context),
                MovieLocalData.getInstance(context));
    }

    public LiveData<List<Movie>> getSearchMovie(String query) {
        MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        Disposable disposable = mReponsitory.getSearchMovie(BuildConfig.API_KEY, query).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(movieReponse -> {
                    movies.setValue(movieReponse.getMovies());
                });
        mDisposable.add(disposable);
        return movies;
    }
}
