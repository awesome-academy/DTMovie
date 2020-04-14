package com.example.dtmovie.data.reponse;

import com.example.dtmovie.data.model.Genres;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenresReponse {
    @SerializedName("genres")
    private List<Genres> mGenresList;

    public GenresReponse(List<Genres> genresList) {
        mGenresList = genresList;
    }

    public List<Genres> getGenresList() {
        return mGenresList;
    }

    public void setGenresList(List<Genres> genresList) {
        mGenresList = genresList;
    }
}
