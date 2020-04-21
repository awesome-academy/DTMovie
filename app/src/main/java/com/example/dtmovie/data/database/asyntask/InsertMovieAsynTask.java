package com.example.dtmovie.data.database.asyntask;

import android.os.AsyncTask;

import com.example.dtmovie.data.database.MovieDao;
import com.example.dtmovie.data.model.Movie;

public class InsertMovieAsynTask extends AsyncTask<Movie, Void, Boolean> {
    private MovieDao mMovieDao;

    public InsertMovieAsynTask(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    protected Boolean doInBackground(Movie... movies) {
        return mMovieDao.insert(movies[0]) != 0;
    }
}
