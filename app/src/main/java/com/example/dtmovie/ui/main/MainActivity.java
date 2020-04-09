package com.example.dtmovie.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dtmovie.R;
import com.example.dtmovie.constant.IntViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        registerListener();
        initViewPager();
    }

    private void registerListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initViewPager() {
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), PagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(IntViewPager.SET_OFF_SCREEN_LIMIT);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setCurrentItem(IntViewPager.FRAGMENT_HOME);
    }

    private void initComponents() {
        mViewPager = findViewById(R.id.viewPager);
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_option:
                mViewPager.setCurrentItem(IntViewPager.FRAGMENT_OPTIONS);
                return true;
            case R.id.menu_home:
                mViewPager.setCurrentItem(IntViewPager.FRAGMENT_HOME);
                return true;
            case R.id.menu_favorite:
                mViewPager.setCurrentItem(IntViewPager.FRAGMENT_FAVORITE);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case IntViewPager.FRAGMENT_OPTIONS:
                mBottomNavigationView.setSelectedItemId(R.id.menu_option);
                break;
            case IntViewPager.FRAGMENT_HOME:
                mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case IntViewPager.FRAGMENT_FAVORITE:
                mBottomNavigationView.setSelectedItemId(R.id.menu_favorite);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
