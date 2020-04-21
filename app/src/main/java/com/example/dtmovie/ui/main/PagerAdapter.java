package com.example.dtmovie.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dtmovie.constant.IntViewPager;
import com.example.dtmovie.ui.favorite.FavoriteMovieFragment;
import com.example.dtmovie.ui.home.HomeFragment;
import com.example.dtmovie.ui.option.OptionsFragment;

import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_FAVORITE;
import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_HOME;
import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_OPTIONS;

public class PagerAdapter extends FragmentPagerAdapter {
    public static final int COUNT = 3;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(@IntViewPager int position) {
        switch (position) {
            case FRAGMENT_OPTIONS:
                return OptionsFragment.getInstance();
            case FRAGMENT_HOME:
                return HomeFragment.getInstance();
            case FRAGMENT_FAVORITE:
                return FavoriteMovieFragment.getInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
