package com.example.dtmovie.ui.movie_details.similar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.FragmentMovieSimilarBinding;
import com.example.dtmovie.ui.home.adapter.ItemMovieListener;
import com.example.dtmovie.ui.home.adapter.MovieAdapter;
import com.example.dtmovie.ui.movie_details.MovieDetailActivity;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

public class SimilarFragment extends Fragment implements ItemMovieListener {
    private FragmentMovieSimilarBinding mBinding;
    private MovieDetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_movie_similar, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerSimilar();
        return mBinding.getRoot();
    }

    private void initRecyclerSimilar() {
        MovieAdapter movieAdapter = new MovieAdapter(getContext());
        movieAdapter.setListener(this);
        mBinding.recyclerSimilar.setAdapter(movieAdapter);
    }

    public static SimilarFragment getInstance() {
        return new SimilarFragment();
    }

    public FragmentMovieSimilarBinding getBinding() {
        return mBinding;
    }

    public void setBinding(FragmentMovieSimilarBinding binding) {
        mBinding = binding;
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        startActivity(MovieDetailActivity.getIntent(getContext(), movie));
    }
}
