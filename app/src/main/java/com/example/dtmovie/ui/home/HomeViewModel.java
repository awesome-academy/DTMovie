package com.example.dtmovie.ui.home;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;
import com.example.dtmovie.ui.home.adapter.MovieTrendingAdapter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    public static final int PAGE_DEFAULT = 1;
    public static final int FROM_INDEX = 0;
    public static final int FROM_TO_INDEX = 5;
    private CompositeDisposable mDisposable;
    private MovieReponsitory mReponsitory;
    private MutableLiveData<List<Movie>> mMovieTrending;
    private MovieTrendingAdapter mTrendingAdapter;
    private ObservableList<Movie> mMovieObservableList = new ObservableArrayList<>();
    private Context mContext;

    public void initViewModel(Context context) {
        mContext = context;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.
                getInstance(MovieRemoteData.getInstance(context), new MovieLocalData());
    }

    LiveData<List<Movie>> getTrendingMovie() {
        if (mMovieTrending == null) {
            mMovieTrending = new MutableLiveData<>();
            loadTrendingMovie();
        }
        return mMovieTrending;
    }

    List<MovieCategory> getCategories() {
        return mReponsitory.getCategories();
    }

    private void loadTrendingMovie() {
        Disposable disposable = mReponsitory.getTrendingMovie().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieReponse -> {
                    mMovieTrending.setValue(movieReponse.getMovies().subList(FROM_INDEX, FROM_TO_INDEX));
                }, throwable -> handeError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void handeError(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public void dispose() {
        mDisposable.dispose();
    }
}
