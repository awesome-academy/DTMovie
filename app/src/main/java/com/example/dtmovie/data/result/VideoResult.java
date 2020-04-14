package com.example.dtmovie.data.result;

import com.example.dtmovie.data.model.Video;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoResult {
    @SerializedName("results")
    private List<Video> mVideos;

    public VideoResult(List<Video> videos) {
        mVideos = videos;
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void setVideos(List<Video> videos) {
        mVideos = videos;
    }
}
