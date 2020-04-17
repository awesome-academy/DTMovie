package com.example.dtmovie.constant;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({IntMovieViewPager.INFOR, IntMovieViewPager.TRAILER
        , IntMovieViewPager.SIMILAR, IntMovieViewPager.CAST, IntMovieViewPager.PRODUCER})
@Retention(RetentionPolicy.SOURCE)
public @interface IntMovieViewPager {
    int INFOR = 0;
    int TRAILER = 1;
    int SIMILAR = 2;
    int CAST = 3;
    int PRODUCER = 4;
}
