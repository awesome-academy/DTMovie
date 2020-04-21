package com.example.dtmovie.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dtmovie.data.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM favorite_movie")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM favorite_movie WHERE id = :movieId LIMIT 1")
    LiveData<Movie> getMovieById(int movieId);
}
