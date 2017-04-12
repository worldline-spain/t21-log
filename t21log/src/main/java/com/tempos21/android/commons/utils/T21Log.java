package com.tempos21.android.commons.utils;

/**
 * Custom Log class
 */
public class T21Log extends Logger {

    /**
     * Initialize the Log
     *
     * @param tag:     Your appName or something You want to appear in log;
     * @param enabled: If you want to enable or disable the log
     */
    public static void initialize(String tag, boolean enabled) {
        Logger.initialize(tag, enabled);
    }

    public static void v(Object... verbose) {
        if (getLogEnabled()) {
            android.util.Log.v(getLogTag(), getLog(verbose));
        }
    }

    public static void d(Object... debug) {
        if (getLogEnabled()) {
            android.util.Log.d(getLogTag(), getLog(debug));
        }
    }


    public static void i(Object... info) {
        if (getLogEnabled()) {
            android.util.Log.i(getLogTag(), getLog(info));
        }
    }

    public static void w(Object... warning) {
        if (getLogEnabled()) {
            android.util.Log.w(getLogTag(), getLog(warning));
        }
    }

    public static void e(Object... error) {
        if (getLogEnabled()) {
            android.util.Log.e(getLogTag(), getLog(error));
        }
    }
}
