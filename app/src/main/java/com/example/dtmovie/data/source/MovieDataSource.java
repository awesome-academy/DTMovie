package com.example.dtmovie.data.source;

import com.example.dtmovie.data.model.GenresReponse;
import com.example.dtmovie.data.model.MovieCategory;
import com.example.dtmovie.data.model.MovieReponse;

import java.util.List;

import io.reactivex.Observable;

public interface MovieDataSource {
    interface Remote {
        Observable<MovieReponse> getMoviesByCategory(String category, int page);

        Observable<MovieReponse> getTrendingMovie();

        Observable<GenresReponse> getMovieGenres();
    }

    interface Local {
        List<MovieCategory> getCategories();
    }
}
