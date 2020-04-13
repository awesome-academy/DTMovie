package com.example.dtmovie.data.model;

import com.google.gson.annotations.SerializedName;

public class Genres {
    @SerializedName("id")
    private int mGenresId;
    @SerializedName("name")
    private String mGenresName;

    public Genres(int genresId, String genresName) {
        mGenresId = genresId;
        mGenresName = genresName;
    }

    public int getGenresId() {
        return mGenresId;
    }

    public void setGenresId(int genresId) {
        mGenresId = genresId;
    }

    public String getGenresName() {
        return mGenresName;
    }

    public void setGenresName(String genresName) {
        mGenresName = genresName;
    }
}
