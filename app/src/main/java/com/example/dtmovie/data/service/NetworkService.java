package com.example.dtmovie.data.service;

import android.content.Context;

public class NetworkService {
    private static RequestApi sRequestApi;

    public static RequestApi getInstance(Context context) {
        if (sRequestApi == null) {
            sRequestApi = ServiceClient.getInstance(context).create(RequestApi.class);
        }
        return sRequestApi;
    }
}
