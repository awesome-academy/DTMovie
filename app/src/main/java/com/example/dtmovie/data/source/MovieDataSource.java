package com.example.dtmovie.data.source;

import com.example.dtmovie.data.model.MovieReponse;

import io.reactivex.Observable;

public interface MovieDataSource {
    interface remote{
        Observable<MovieReponse> getMoviesByCategory(String category,int page);
    }
}
