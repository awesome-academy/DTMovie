package com.example.dtmovie.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_FAVORITE;
import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_HOME;
import static com.example.dtmovie.constant.IntViewPager.FRAGMENT_OPTIONS;

@IntDef({FRAGMENT_OPTIONS,
        FRAGMENT_HOME, FRAGMENT_FAVORITE})
@Retention(RetentionPolicy.SOURCE)
public @interface IntViewPager {
    int FRAGMENT_OPTIONS = 0;
    int FRAGMENT_HOME = 1;
    int FRAGMENT_FAVORITE = 2;
    int SET_OFF_SCREEN_LIMIT = 3;
}
