package com.example.dtmovie.ui.movie_details.infor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dtmovie.R;
import com.example.dtmovie.databinding.FragmentMovieInforBinding;
import com.example.dtmovie.ui.movie_details.MovieDetailViewModel;

import java.util.ArrayList;

public class MovieInforFragment extends Fragment implements View.OnClickListener, OnFavoriteListener {
    private FragmentMovieInforBinding mBinding;
    private MovieDetailViewModel mViewModel;
    private static final String MSG_ADDED = "Added to Favorite";
    public static final String MSG_REMOVED = "Removed from Favorite";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater
            , @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_movie_infor, container, false);
        mBinding.setViewModel(mViewModel);
        initRecyclerGenres();
        return mBinding.getRoot();
    }

    private void initRecyclerGenres() {
        mBinding.recyclerGenres.setAdapter(new MovieGenresAdapter(getContext(), new ArrayList<>()));
        mViewModel.setOnFavoriteListener(this);
        mBinding.imageFavorive.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        mViewModel.changeFavorite();
    }

    @Override
    public void onFavoriteClick(boolean isFavorite) {
        Toast.makeText(getContext(), isFavorite ? MSG_ADDED : MSG_REMOVED, Toast.LENGTH_SHORT).show();
    }
}
