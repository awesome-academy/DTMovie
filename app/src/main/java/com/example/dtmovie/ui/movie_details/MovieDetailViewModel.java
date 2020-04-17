package com.example.dtmovie.ui.movie_details;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailViewModel extends BaseViewModel {
    private CompositeDisposable mDisposable;
    private Context mContext;
    private MovieReponsitory mReponsitory;
    private OnTrailerListener mOnTrailerListener;
    public ObservableField<Movie> mMovies = new ObservableField<>();

    public void initViewModel(Context context, Movie movie, OnTrailerListener listener) {
        mOnTrailerListener = listener;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.
                getInstance(MovieRemoteData.getInstance(context), new MovieLocalData());
        mContext = context;
        load(movie.getId());
    }

    private void load(int id) {
        Disposable disposable = mReponsitory.getMovieDetail(id).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    mMovies.set(movie);
                    if (!movie.getVideoResult().getVideos().isEmpty()) {
                        mOnTrailerListener.
                                onCreateTrailer(movie.getVideoResult().getVideos().get(0).getKey());
                    }
                }, throwable -> handlerError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void handlerError(String message) {
        Toast.makeText(mContext, "" + message.toString(), Toast.LENGTH_SHORT).show();
    }

    void destroy() {
        mDisposable.dispose();
    }
}
