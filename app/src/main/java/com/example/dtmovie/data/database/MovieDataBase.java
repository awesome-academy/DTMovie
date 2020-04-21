package com.example.dtmovie.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dtmovie.data.model.Movie;

@Database(entities = Movie.class, exportSchema = false, version = 1)
public abstract class MovieDataBase extends RoomDatabase {
    public static final String DB_NAME = "movie_database";

    private static MovieDataBase sIntance;

    public abstract MovieDao MovieDao();

    public static synchronized MovieDataBase getInstance(Context context) {
        if (sIntance == null) {
            sIntance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDataBase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return sIntance;
    }
}
