package com.example.dtmovie.ui.movie_details.cast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.databinding.FragmentMoiveCastBinding;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

import java.util.ArrayList;

public class MovieCastFragment extends Fragment {
    private FragmentMoiveCastBinding mBinding;
    private MovieDetailViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_moive_cast, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerView();
        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        MovieCastApdapter apdapter = new MovieCastApdapter(getContext(),new ArrayList<>());
        mBinding.recyclerCast.setAdapter(apdapter);
    }

    public static MovieCastFragment getInstance() {
        return new MovieCastFragment();
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }
}
