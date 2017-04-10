package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.app.Application;

import java.io.File;

public class SampleApplication extends Application {

    private static final String LOG_TAG = "[SAMPLE_APP]";

    private static final String LOG_PATH = "logs";

    private static final String LOG_FILE_NAME = "SampleApp.log";

    private File logFile;

    @Override
    public void onCreate() {
        super.onCreate();
        File logsPath = new File(getFilesDir(), LOG_PATH);
        logsPath.mkdir();
        logFile = new File(logsPath, LOG_FILE_NAME);
        T21Log.initialize(LOG_TAG, BuildConfig.DEBUG, logFile);
    }

    public File getLogFile() {
        return logFile;
    }
}
