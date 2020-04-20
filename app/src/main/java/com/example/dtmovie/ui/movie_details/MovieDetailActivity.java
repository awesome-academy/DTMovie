package com.example.dtmovie.ui.movie_details;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import com.example.dtmovie.R;
import com.example.dtmovie.constant.IntViewPager;
import com.example.dtmovie.data.model.Movie;
import com.example.dtmovie.databinding.ActivityDetailBinding;
import com.example.dtmovie.ui.main.MainActivity;
import com.example.dtmovie.ui.movie_details.cast.MovieCastFragment;
import com.example.dtmovie.ui.movie_details.infor.MovieInforFragment;
import com.example.dtmovie.ui.movie_details.producer.MovieProducerFragment;
import com.example.dtmovie.ui.movie_details.similar.SimilarFragment;
import com.example.dtmovie.ui.movie_details.trailer.TrailerFragment;

public class MovieDetailActivity extends AppCompatActivity implements OnTrailerListener {
    private static final String EXTRA_MOVIE_DETAIL = "extra_movie_detail";
    public static final String EXTRA_MOVIE = "extra_movie";
    private MovieDetailViewModel mViewModel;
    private ActivityDetailBinding mBinding;
    private MoviePagerAdapter mMoviePagerAdapter;
    private Movie mMovie;
    private YoutubeFragment mYoutubeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        mYoutubeFragment = (YoutubeFragment) getFragmentManager()
                .findFragmentById(R.id.fragment_youtube);
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
        MovieProducerFragment movieProducerFragment = MovieProducerFragment.getInstance();
        movieProducerFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(movieProducerFragment);
    }

    private void initMovieCast() {
        MovieCastFragment movieCastFragment = MovieCastFragment.getInstance();
        movieCastFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(movieCastFragment);

    }

    private void initMovieSimilar() {
        SimilarFragment similarFragment = SimilarFragment.getInstance();
        similarFragment.setViewModel(mViewModel);
        mMoviePagerAdapter.addFragment(similarFragment);
    }

    private void initMovieTrailer() {
        TrailerFragment trailerFragment = TrailerFragment.getInstance();
        trailerFragment.setViewModel(mViewModel);
        trailerFragment.setListener(this);
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
        mViewModel.initViewModel(this, mMovie, this);
    }

    private void initActionBar() {
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setTitle(mMovie.getTitle());
        mBinding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        mBinding.toolbar.setNavigationOnClickListener(v -> {
            Intent intent = MainActivity.getIntent(getApplicationContext());
            intent.addFlags(Intent.
                            FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
                }
        );
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

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mYoutubeFragment.setFullScreen(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    public void onCreateTrailer(String mTrailerKey) {
        mYoutubeFragment.setTrailerId(mTrailerKey);
    }

    @Override
    public void onPlayTrailer(String mTrailerKey) {
        mYoutubeFragment.setTrailerId(mTrailerKey);
        mYoutubeFragment.playTrailer();
    }
}
