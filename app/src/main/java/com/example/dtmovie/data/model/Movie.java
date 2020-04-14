package com.example.dtmovie.data.model;

import com.example.dtmovie.data.result.CastResult;
import com.example.dtmovie.data.result.SimilarResult;
import com.example.dtmovie.data.result.VideoResult;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    @SerializedName("id")
    private int mId;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("genres")
    private List<Genres> mGenres;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("production_companies")
    private List<Company> mProductionCompanies;

    @SerializedName("vote_average")
    private double mVoteAverAge;

    @SerializedName("overview")
    private String mOverView;

    @SerializedName("release_date")
    private String mReleaseData;

    @SerializedName("credits")
    private CastResult mCastResult;

    @SerializedName("videos")
    private VideoResult mVideoResult;

    @SerializedName("similar")
    private SimilarResult mSimilarResult;

    public Movie() {
    }

    public Movie(int id, String backdropPath, String posterPath,
                 List<Genres> genres, String title, List<Company> productionCompanies,
                 double voteAverAge, String overView, String releaseData,
                 CastResult castResult, VideoResult videoResult, SimilarResult similarResult) {
        mId = id;
        mBackdropPath = backdropPath;
        mPosterPath = posterPath;
        mGenres = genres;
        mTitle = title;
        mProductionCompanies = productionCompanies;
        mVoteAverAge = voteAverAge;
        mOverView = overView;
        mReleaseData = releaseData;
        mCastResult = castResult;
        mVideoResult = videoResult;
        mSimilarResult = similarResult;
    }

    public List<Genres> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genres> genres) {
        mGenres = genres;
    }

    public List<Company> getProductionCompanies() {
        return mProductionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        mProductionCompanies = productionCompanies;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
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

    public CastResult getCastResult() {
        return mCastResult;
    }

    public void setCastResult(CastResult castResult) {
        mCastResult = castResult;
    }

    public VideoResult getVideoResult() {
        return mVideoResult;
    }

    public void setVideoResult(VideoResult videoResult) {
        mVideoResult = videoResult;
    }

    public SimilarResult getSimilarResult() {
        return mSimilarResult;
    }

    public void setSimilarResult(SimilarResult similarResult) {
        mSimilarResult = similarResult;
    }
}
