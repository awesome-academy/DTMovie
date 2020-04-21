package com.example.dtmovie.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.constant.Constant;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.databinding.FragmentHomeBinding;
import com.example.dtmovie.ui.home.adapter.CategoryAdapter;
import com.example.dtmovie.ui.home.adapter.GenresAdapter;
import com.example.dtmovie.ui.home.adapter.MovieTrendingAdapter;
import com.example.dtmovie.ui.home.adapter.MovieTrendingListener;
import com.example.dtmovie.ui.movie_details.MovieDetailActivity;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment implements MovieTrendingListener {
    private HomeViewModel mViewModel;
    private MovieTrendingAdapter mTrendingAdapter;
    private FragmentHomeBinding mBinding;
    private int mCurrentSide;
    private Handler mHandler;
    private List<MovieCategory> mMovieCategoryList;
    private CategoryAdapter mCategoryAdapter;
    private GenresAdapter mGenresAdapter;
    private static final CharSequence TITTLE_SPACE = " ";
    private static final int DEFAULT_SCROLL_RANGE = -1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentHomeBinding) getViewDataBinding();
        setAdapter();
        hindeSlide();
        initToolbar();
    }

    private void initToolbar() {
        mBinding.toolbar.inflateMenu(R.menu.menu_tool_bar);
        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_search) {
                    Toast.makeText(getContext(), "Search", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void hindeSlide() {
        mBinding.appBar.addOnOffsetChangedListener(getAppBarListener());
    }

    private AppBarLayout.OnOffsetChangedListener getAppBarListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
            int scrollRange = DEFAULT_SCROLL_RANGE;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == DEFAULT_SCROLL_RANGE) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mBinding.collapsingToolbar.setTitle(getString(R.string.app_name));
                } else {
                    mBinding.collapsingToolbar.setTitle(TITTLE_SPACE);
                }
            }
        };
    }


    private void setAdapter() {
        mTrendingAdapter = new MovieTrendingAdapter(getActivity(), this);
        mBinding.viewPager.setAdapter(mTrendingAdapter);
        mCurrentSide = mTrendingAdapter.getCurrent();
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager, true);
        initTimerChangerSlide();
        mMovieCategoryList = mViewModel.getCategories();
        mCategoryAdapter = new CategoryAdapter(getContext());
        mCategoryAdapter.update(mMovieCategoryList);
        mBinding.recyclerCategory.setAdapter(mCategoryAdapter);
        mBinding.recyclerCategory.hasFixedSize();

        mGenresAdapter = new GenresAdapter(getContext(), new ObservableArrayList<>());
        mBinding.recyclerCategory.setNestedScrollingEnabled(false);
        mBinding.recyclerGenres.setAdapter(mGenresAdapter);
        mBinding.recyclerGenres.hasFixedSize();
    }

    private void initTimerChangerSlide() {
        mHandler = new Handler();
        Runnable runnable = () -> {
            if (mCurrentSide == Constant.CURRENT_SLIDE_MAX) {
                mCurrentSide = Constant.CURRENT_SLIDE_DEFAUL;
            }
            mBinding.viewPager.setCurrentItem(mCurrentSide++, true);
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(runnable);
            }
        }, Constant.TIME_DELAY, Constant.TIME_PERIOD);
    }

    @Override
    protected int getVaribale() {
        return 0;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeViewModel getViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                Objects.requireNonNull(getActivity()).getApplication()).
                create(HomeViewModel.class);
        mViewModel.initViewModel(getContext());
        fetchData();
        return mViewModel;
    }

    private void fetchData() {
        mViewModel.getTrendingMovie().observe(this, movies -> {
            mTrendingAdapter.update(movies);
        });
        mViewModel.getGenres().observe(this, genres -> {
            mGenresAdapter.update(genres);
        });
    }

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.dispose();
    }

    @Override
    public void onMovieTrendingListener(Movie movie) {
        startActivity(MovieDetailActivity.getIntent(getContext(), movie));
    }
}
