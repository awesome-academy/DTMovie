package com.example.dtmovie.ui.movie_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.constant.IntViewPager;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.ActivityDetailBinding;
import com.example.dtmovie.ui.movie_details.cast.CastMovieFragment;
import com.example.dtmovie.ui.movie_details.infor.MovieInforFragment;
import com.example.dtmovie.ui.movie_details.producer.ProducerFragment;
import com.example.dtmovie.ui.movie_details.similar.SimilarFragment;
import com.example.dtmovie.ui.movie_details.trailer.TrailerFragment;

public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";
    public static final String EXTRA_MOVIE = "extra_movie";
    private MovieDetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    private MoviePagerAdapter mMoviePagerAdapter;
    private Movie mMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        initData();
        initViewModel();
        initActionBar();
        initViewPager();
    }

    private void initViewPager() {
        mMoviePagerAdapter = new MoviePagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        initMovieInfor();
        initMovieTrailer();
        initMovieSimilar();
        initMovieCast();
        initMovieProducer();
        mBinding.viewPager.setAdapter(mMoviePagerAdapter);
        mBinding.viewPager.setOffscreenPageLimit(IntViewPager.SET_OFF_SCREEN_LIMIT);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);
    }

    private void initMovieProducer() {
        ProducerFragment producerFragment = ProducerFragment.getInstance();
        producerFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(producerFragment);
    }

    private void initMovieCast() {
        CastMovieFragment castMovieFragment = CastMovieFragment.getInstance();
        castMovieFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(castMovieFragment);

    }

    private void initMovieSimilar() {
        SimilarFragment similarFragment = SimilarFragment.getInstance();
        similarFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(similarFragment);
    }

    private void initMovieTrailer() {
        TrailerFragment trailerFragment = TrailerFragment.getInstance();
        trailerFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(trailerFragment);
    }

    private void initMovieInfor() {
        MovieInforFragment inforFragment = MovieInforFragment.getInstance();
        inforFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(inforFragment);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewModel.destroy();
    }
}
