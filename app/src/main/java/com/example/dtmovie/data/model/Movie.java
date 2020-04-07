package com.example.dtmovie.data.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private int mId;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("vote_average")
    private double mVoteAverAge;

    @SerializedName("overview")
    private String mOverView;

    @SerializedName("release_date")
    private String mReleaseData;

    public Movie() {
    }

    public Movie(int id, String backdropPath, String title, double voteAverAge, String overView, String releaseData) {
        mId = id;
        mBackdropPath = backdropPath;
        mTitle = title;
        mVoteAverAge = voteAverAge;
        mOverView = overView;
        mReleaseData = releaseData;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getVoteAverAge() {
        return mVoteAverAge;
    }

    public void setVoteAverAge(double voteAverAge) {
        mVoteAverAge = voteAverAge;
    }

    public String getOverView() {
        return mOverView;
    }

    public void setOverView(String overView) {
        mOverView = overView;
    }

    public String getReleaseData() {
        return mReleaseData;
    }

    public void setReleaseData(String releaseData) {
        mReleaseData = releaseData;
    }
}
