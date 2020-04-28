package com.example.dtmovie.ui.movie_details;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;
import com.example.dtmovie.ui.movie_details.infor.OnFavoriteListener;

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
    private OnFavoriteListener mOnFavoriteListener;
    public ObservableBoolean isFavorite = new ObservableBoolean(false);


    public void initViewModel(Context context, Movie movie, OnTrailerListener listener) {
        mOnTrailerListener = listener;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.
                getInstance(MovieRemoteData.getInstance(context)
                        , MovieLocalData.getInstance(context));
        mContext = context;
        load(movie.getId());
    }

    private void load(int id) {
        Disposable disposable = mReponsitory.getDetailMovie(id).
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
        Toast.makeText(mContext, message.toString(), Toast.LENGTH_SHORT).show();
    }

    LiveData<Movie> getMoviebyId(int MovieId) {
        return mReponsitory.getMovieById(MovieId);
    }

    public void changeFavorite() {
        isFavorite.set(!isFavorite.get());
        if (isFavorite.get()) {
            mReponsitory.addFavoriteMovie(mMovies.get());
        } else {
            mReponsitory.deleteFavoriteMovie(mMovies.get());
        }
        mOnFavoriteListener.onFavoriteClick(isFavorite.get());
    }

    public void setOnFavoriteListener(OnFavoriteListener onFavoriteListener) {
        mOnFavoriteListener = onFavoriteListener;
    }

    void destroy() {
        mDisposable.dispose();
    }
}
