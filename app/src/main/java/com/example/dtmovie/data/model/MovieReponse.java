package com.example.dtmovie.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReponse {
    @SerializedName("results")
    private List<Movie> mMovies;

    public MovieReponse(List<Movie> movies) {
        mMovies = movies;
    }

    public List<Movie> getMovies() {
        return mMovies;
    }

    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }
}
