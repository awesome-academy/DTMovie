package com.example.dtmovie.ui.favorite;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.base.BaseViewModel;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.FragmentFavoriveBinding;
import com.example.dtmovie.ui.home.adapter.ItemMovieListener;
import com.example.dtmovie.ui.movie_details.MovieDetailActivity;

import java.util.List;
import java.util.Objects;

public class FavoriteMovieFragment extends BaseFragment implements ItemMovieListener {
    private FavoriteMovieViewModel mViewModel;
    private FragmentFavoriveBinding mBinding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentFavoriveBinding) getViewDataBinding();
        initcc();
        initTootbar();
    }

    private void initTootbar() {
        mBinding.toolbar.inflateMenu(R.menu.menu_tool_bar);
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_search){
                }
                return false;
            }
        });
    }

    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_favorive;
    }

    @Override
    protected BaseViewModel getViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.
                getInstance(Objects.requireNonNull(getActivity()).
                        getApplication()).create(FavoriteMovieViewModel.class);
        mViewModel.initViewModel(getContext());

        return mViewModel;
    }

    private void initcc() {
        FavoriteMovieAdapter adapter = new FavoriteMovieAdapter(getContext());
        adapter.setListener(this);
        mBinding.recyclerMovie.setAdapter(adapter);
        mBinding.recyclerMovie.hasFixedSize();
        mViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.update(movies);
            }
        });
    }

    public static FavoriteMovieFragment getInstance() {
        return new FavoriteMovieFragment();
    }

    @Override
    public void onItemMovieClick(Movie movie) {
        startActivity(MovieDetailActivity.getIntent(getContext(),movie));
    }
}
