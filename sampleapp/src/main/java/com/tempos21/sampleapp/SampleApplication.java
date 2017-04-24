package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.app.Application;

public class SampleApplication extends Application {

    private static final String LOG_TAG = "[SAMPLE_APP]";

    @Override
    public void onCreate() {
        super.onCreate();
        T21Log.initialize(LOG_TAG, BuildConfig.DEBUG, BuildConfig.DEBUG, this);
    }
}
