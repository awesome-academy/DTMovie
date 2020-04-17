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
import com.example.dtmovie.databinding.FragmentSimilarBinding;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

public class SimilarFragment extends Fragment {
    private FragmentSimilarBinding mBinding;
    private MovieDetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_similar, container, false);
        mBinding.setViewModel(mViewModel);
        return mBinding.getRoot();
    }

    public static SimilarFragment getInstance() {
        return new SimilarFragment();
    }

    public FragmentSimilarBinding getBinding() {
        return mBinding;
    }

    public void setBinding(FragmentSimilarBinding binding) {
        mBinding = binding;
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
