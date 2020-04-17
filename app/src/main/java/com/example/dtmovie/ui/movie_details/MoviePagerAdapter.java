package com.example.dtmovie.ui.movie_details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.dtmovie.constant.ITabsString;
import com.example.dtmovie.constant.IntMovieViewPager;

import java.util.ArrayList;
import java.util.List;

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MoviePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mFragmentList = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(@IntMovieViewPager int position) {
        switch (position) {
            case IntMovieViewPager.INFOR:
                return ITabsString.TITLE_INFO;
            case IntMovieViewPager.TRAILER:
                return ITabsString.TITLE_TRAILER;
            case IntMovieViewPager.SIMILAR:
                return ITabsString.TITLE_SIMILAR;
            case IntMovieViewPager.CAST:
                return ITabsString.TITLE_CAST;
            case IntMovieViewPager.PRODUCER:
                return ITabsString.TITLE_PRODUCER;
            default:
                return super.getPageTitle(position);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
