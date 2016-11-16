package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.app.Application;

public class SampleApplication extends Application {

    private static final String LOG_TAG = "[SAMPLE_APP]";

    @Override
    public void onCreate() {
        super.onCreate();

        initLog();
    }

    private void initLog() {
        T21Log.setLogEnabled(BuildConfig.DEBUG);
        T21Log.setLogTag(LOG_TAG);
    }
}
