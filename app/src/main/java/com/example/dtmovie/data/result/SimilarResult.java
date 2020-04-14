package com.example.dtmovie.data.result;

import com.example.dtmovie.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SimilarResult {

    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<Movie> mSimilarsMovie;

    public SimilarResult(int page, List<Movie> similarsMovie) {
        mPage = page;
        mSimilarsMovie = similarsMovie;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<Movie> getSimilarsMovie() {
        return mSimilarsMovie;
    }

    public void setSimilarsMovie(List<Movie> similarsMovie) {
        mSimilarsMovie = similarsMovie;
    }
}
