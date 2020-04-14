package com.example.dtmovie.data.result;

import com.example.dtmovie.data.model.Actor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResult {
    @SerializedName("cast")
    private List<Actor> mActors;

    public List<Actor> getActors() {
        return mActors;
    }

    public void setActors(List<Actor> actors) {
        mActors = actors;
    }
}
