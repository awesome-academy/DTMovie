package com.example.dtmovie.ui.home;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.responsitory.MovieReponsitory;
import com.example.dtmovie.data.source.local.MovieLocalData;
import com.example.dtmovie.data.source.remote.MovieRemoteData;
import com.example.dtmovie.ui.home.adapter.MovieAdapter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ItemCategoryViewModel extends BaseViewModel {
    public static final int PAGE_DEFAULT = 1;
    public final ObservableField<MovieCategory>
            mCategoryMovieObservableField = new ObservableField<>();
    private MutableLiveData<List<Movie>> mMovie;
    private Context mContext;
    private CompositeDisposable mDisposable;
    private MovieReponsitory mReponsitory;
    private MovieAdapter mMovieAdapter;

    public ItemCategoryViewModel(Context context, MovieCategory movieCategory) {
        mContext = context;
        mDisposable = new CompositeDisposable();
        mReponsitory = MovieReponsitory.
                getInstance(MovieRemoteData.getInstance(context), new MovieLocalData(context));
    }

    public LiveData<List<Movie>> getMovie(MovieCategory category) {
        if (mMovie == null) {
            mMovie = new MutableLiveData<>();
            loadMovies(category);
        }
        return mMovie;
    }

    private void loadMovies(MovieCategory movieCategory) {
        Disposable disposable = mReponsitory
                .getMoviesByCategory(movieCategory.getCategoryKey(), PAGE_DEFAULT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieReponse -> {
                    mMovie.setValue(movieReponse.getMovies());
                }, throwable -> handlerError(throwable.getMessage()));
        mDisposable.add(disposable);
    }

    private void handlerError(String message) {
        Toast.makeText(mContext, message.toString(), Toast.LENGTH_SHORT).show();
    }

    public void setItemListCategoryBinding(MovieCategory movieCategory) {
        mCategoryMovieObservableField.set(movieCategory);
    }

    public MovieCategory getCategoryMovie() {
        return mCategoryMovieObservableField.get();
    }
}
