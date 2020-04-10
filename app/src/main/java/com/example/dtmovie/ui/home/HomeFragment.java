package com.example.dtmovie.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dtmovie.R;
import com.example.dtmovie.base.BaseFragment;
import com.example.dtmovie.constant.Constant;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.FragmentHomeBinding;
import com.example.dtmovie.ui.home.adapter.CategoryAdapter;
import com.example.dtmovie.ui.home.adapter.MovieTrendingAdapter;
import com.example.dtmovie.ui.home.adapter.MovieTrendingListener;

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
    private RecyclerView mRecyclerCategory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = (FragmentHomeBinding) getViewDataBinding();
        setAdapter();
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
        Toast.makeText(getContext(), "" + movie.getTitle().toString(), Toast.LENGTH_SHORT).show();
    }
}
