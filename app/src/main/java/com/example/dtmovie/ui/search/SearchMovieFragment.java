package com.example.dtmovie.ui.search;

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
import com.example.dtmovie.databinding.FragmentMovieSearchBinding;
import com.example.dtmovie.ui.favorite.FavoriteMovieAdapter;

import java.util.List;
import java.util.Objects;

public class SearchMovieFragment extends BaseFragment {
    private FragmentMovieSearchBinding mBinding;
    private SearchMovieViewModel mViewModel;
    private String mQuery ="a";
    private FavoriteMovieAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentMovieSearchBinding) getViewDataBinding();
        initToolbar();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mBinding.imageClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                mBinding.editQuery.setText(null);
            }
        });
        mAdapter = new FavoriteMovieAdapter(getContext());
        mBinding.recyclerMovie.setAdapter(mAdapter);
        mBinding.recyclerMovie.hasFixedSize();
        mViewModel.getSearchMovie(mQuery).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.update(movies);
            }
        });
    }

    private void initToolbar() {
        mBinding.toolbar.inflateMenu(R.menu.menu_tool_bar);
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_search) {
                    mQuery = String.valueOf(mBinding.editQuery.getText());
                    Moviesearch(mQuery);
                }
                return false;
            }
        });
    }

    private void Moviesearch(String query) {
        mViewModel.getSearchMovie(query).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                mAdapter.update(movies);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_movie_search;
    }

    @Override
    protected BaseViewModel getViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.
                getInstance(Objects.requireNonNull(getActivity().
                        getApplication())).create(SearchMovieViewModel.class);
        mViewModel.initViewModel(getContext());
        return mViewModel;
    }

    public static SearchMovieFragment getInstance() {
        return new SearchMovieFragment();
    }
}
