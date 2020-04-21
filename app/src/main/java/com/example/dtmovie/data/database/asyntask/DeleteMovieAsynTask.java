package com.example.dtmovie.data.database.asyntask;

import android.os.AsyncTask;

import com.example.dtmovie.data.database.MovieDao;
import com.example.dtmovie.data.model.Movie;

public class DeleteMovieAsynTask extends AsyncTask<Movie, Void, Void> {
    private MovieDao mMovieDao;

    public DeleteMovieAsynTask(MovieDao movieDao) {
        mMovieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        mMovieDao.delete(movies[0]);
        return null;
    }
}
