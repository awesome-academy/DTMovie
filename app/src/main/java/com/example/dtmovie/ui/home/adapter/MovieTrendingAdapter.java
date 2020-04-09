package com.example.dtmovie.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.ItemMovieTrendingBinding;
import com.example.dtmovie.ui.home.ItemMovieViewModel;

import java.util.List;

public class MovieTrendingAdapter extends PagerAdapter implements View.OnClickListener {
    private ObservableList<Movie> mMovies;
    private LayoutInflater mLayoutInflater;
    private ItemMovieTrendingBinding mBinding;
    private MovieTrendingListener mMovieTrendingListener;
    private int mCurrent;

    public MovieTrendingAdapter(Context context, MovieTrendingListener movieTrendingListener) {
        mMovies = new ObservableArrayList<>();
        mLayoutInflater = LayoutInflater.from(context);
        mMovieTrendingListener = movieTrendingListener;
    }

    public void update(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    public int getCurrent() {
        return mCurrent;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_movie_trending, container, false);
        if (mBinding.getViewModel() == null) {
            mBinding.setViewModel(new ItemMovieViewModel());
        }
        mBinding.getViewModel().setMovie(mMovies.get(position));
        mCurrent = position;
        mBinding.executePendingBindings();
        mBinding.imageMovie.setOnClickListener(this);
        return mBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public int getCount() {
        return mMovies != null ? mMovies.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void onClick(View v) {
        mMovieTrendingListener.onMovieTrendingListener(mBinding.getViewModel().getMovie());
    }
}
