package com.example.dtmovie.ui.movie_details.infor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.databinding.FragmentMovieInforBinding;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

public class MovieInforFragment extends Fragment {
    private FragmentMovieInforBinding mBinding;
    private MovieDetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_movie_infor, container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static MovieInforFragment getInstance() {
        return new MovieInforFragment();
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
