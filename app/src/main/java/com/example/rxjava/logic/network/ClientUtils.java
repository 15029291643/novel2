package com.example.rxjava.logic.network;

import okhttp3.OkHttpClient;

public class ClientUtils {
    private static volatile OkHttpClient sInstance;

    public static OkHttpClient getInstance() {
        if (sInstance == null) {
            synchronized (ClientUtils.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpClient();
                }
            }
        }
        return sInstance;
    }
}
