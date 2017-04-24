package com.tempos21.android.commons.utils;

/**
 * Custom Log class
 */
class AndroidConsoleLogger extends Logger {

    private static boolean ENABLED = BuildConfig.LOG_ENABLED;

    /**
     * Initialize the AndroidConsoleLog
     *
     * @param tag:     Your appName or something you want to appear in log
     * @param enabled: If you want to enable or disable the Android console log
     */
    static void initialize(String tag, boolean enabled) {
        Logger.initialize(tag);
        ENABLED = enabled;
    }

    static void v(Object... verbose) {
        if (ENABLED) {
            android.util.Log.v(getLogTag(), getLog(verbose));
        }
    }

    static void d(Object... debug) {
        if (ENABLED) {
            android.util.Log.d(getLogTag(), getLog(debug));
        }
    }


    static void i(Object... info) {
        if (ENABLED) {
            android.util.Log.i(getLogTag(), getLog(info));
        }
    }

    static void w(Object... warning) {
        if (ENABLED) {
            android.util.Log.w(getLogTag(), getLog(warning));
        }
    }

    static void e(Object... error) {
        if (ENABLED) {
            android.util.Log.e(getLogTag(), getLog(error));
        }
    }
}
