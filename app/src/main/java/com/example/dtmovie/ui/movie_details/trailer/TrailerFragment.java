package com.example.dtmovie.ui.movie_details.trailer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.databinding.FragmentTrailerBinding;
import com.example.dtmovie.ui.home.adapter.MovieAdapter;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;
import com.example.dtmovie.ui.movie_details.OnTrailerListener;

import java.util.ArrayList;

public class TrailerFragment extends Fragment {
    private FragmentTrailerBinding mBinding;
    private MovieDetailViewModel mViewModel;
    private OnTrailerListener mListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_trailer, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerMovieTrailer();
        return mBinding.getRoot();
    }

    private void initRecyclerMovieTrailer() {
        mBinding.recyclerTrailer.setAdapter(new MoiveTrailerAdapter(new ArrayList<>(),
                getContext(),mListener));
    }

    public static TrailerFragment getInstance() {
        return new TrailerFragment();
    }

    public FragmentTrailerBinding getBinding() {
        return mBinding;
    }

    public void setBinding(FragmentTrailerBinding binding) {
        mBinding = binding;
    }

    public MovieDetailViewModel getViewModel() {
        return mViewModel;
    }

    public void setViewModel(MovieDetailViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void setListener(OnTrailerListener listener) {
        mListener = listener;
    }
}
