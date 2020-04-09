package com.example.dtmovie.ui.home;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    public static final int PAGE_DEFAULT = 1;
    private CompositeDisposable mDisposable;
    private MovieReponsitory mReponsitory;
    private MutableLiveData<List<Movie>> mMovieTrending;
    private ObservableList<Movie> mMovieObservableList = new ObservableArrayList<>();
    private Context mContext;

    public void initViewModel(Context context) {
        mContext = context;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.getInstance(MovieRemoteData.getInstance(context));
        loadMovie();
    }

    private void loadMovie() {
        loadTopRate();
    }

    LiveData<List<Movie>> getTopMovieTrending() {
        if (mMovieTrending == null) {
            mMovieTrending = new MutableLiveData<>();
            loadMovieTrending();
        }
        return mMovieTrending;
    }

    private void loadMovieTrending() {
        Disposable disposable = mReponsitory.getMovieTrending().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieReponse -> {
                    mMovieTrending.setValue(movieReponse.getMovies().subList(1, 10));
                }, throwable -> handeError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void loadTopRate() {
        Disposable disposable = mReponsitory.getMoviesByCategory("now_playing", PAGE_DEFAULT)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieReponse -> {
                    mMovieObservableList.addAll(movieReponse.getMovies());
                }, throwable -> handeError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void handeError(String message) {
        Toast.makeText(mContext, "" + message, Toast.LENGTH_SHORT).show();
    }

    public void dispose() {
        mDisposable.dispose();
    }
}
