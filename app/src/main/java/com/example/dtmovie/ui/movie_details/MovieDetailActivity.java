package com.example.dtmovie.ui.movie_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.ActivityDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";
    public static final String EXTRA_MOVIE = "extra_movie";
    private MovieDetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    private Movie mMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        initData();
        initViewModel();
        initActionBar();
    }

    private void initViewModel() {
        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(MovieDetailViewModel.class);
        mViewModel.initViewModel(this, mMovie);
    }

    private void initActionBar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setTitle(mMovie.getTitle());
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mBinding.toolbar.setNavigationOnClickListener(v -> onBackPressed());
    }

    private void initData() {
        Bundle bundle = getIntent().getBundleExtra(EXTRA_MOVIE_DETAIL);
        mMovie = (Movie) bundle.getSerializable(EXTRA_MOVIE);
    }

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context.getApplicationContext(), MovieDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_MOVIE, movie);
        intent.putExtra(EXTRA_MOVIE_DETAIL, bundle);
        return intent;
    }
}
