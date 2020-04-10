package com.example.dtmovie.data.source.local;

import com.example.dtmovie.constant.CategoryKey;
import com.example.dtmovie.constant.CategoryName;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.source.MovieDataSource;

import java.util.ArrayList;
import java.util.List;

public class MovieLocalData implements MovieDataSource.Local {

    @Override
    public List<MovieCategory> getCategories() {
        List<MovieCategory> movieCategories = new ArrayList<>();
        MovieCategory now_playing = new MovieCategory(CategoryName.TITLE_NOW_PLAYING
                , CategoryKey.CATEGORY_NOW_PLAYING);
        MovieCategory popular = new MovieCategory(CategoryName.TITLE_POPULAR
                , CategoryKey.CATEGORY_POPULAR);
        MovieCategory top_rated = new MovieCategory(CategoryName.TITLE_TOP_RATE
                , CategoryKey.CATEGORY_TOP_RATE);
        MovieCategory upcoming = new MovieCategory(CategoryName.TITLE_UP_COMING
                , CategoryKey.CATEGORY_UP_COMING);
        movieCategories.add(now_playing);
        movieCategories.add(popular);
        movieCategories.add(top_rated);
        movieCategories.add(upcoming);
        return movieCategories;
    }
}
