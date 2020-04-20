package com.example.dtmovie.ui.movie_details.producer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.databinding.FragmentMovieProducerBinding;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

import java.util.ArrayList;

public class MovieProducerFragment extends Fragment {
    private FragmentMovieProducerBinding mBinding;
    private MovieDetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_movie_producer, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerProduce();
        return mBinding.getRoot();
    }

    private void initRecyclerProduce() {
        MovieProduceAdapter adapter = new MovieProduceAdapter(new ArrayList<>(),getContext());
        mBinding.recyclerProduce.setAdapter(adapter);
    }

    public static MovieProducerFragment getInstance() {
        return new MovieProducerFragment();
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
