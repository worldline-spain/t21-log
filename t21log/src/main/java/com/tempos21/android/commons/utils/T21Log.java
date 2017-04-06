package com.tempos21.android.commons.utils;

import java.io.File;

/**
 * Custom Log class
 */
public class T21Log {

    private static final String BLANK = " ";

    private static boolean LOG_ENABLED = BuildConfig.LOG_ENABLED;

    private static String logTag = null;

    public static String getLogTag() {
        if (logTag == null) {
            return (T21Log.class.getPackage().getName());
        }
        return logTag;
    }

    /**
     * Initialize the Log
     *
     * @param tag:     Your appName or something You want to appear in log;
     * @param enabled: If you want to enable or disable the log
     */
    public static void initialize(String tag, boolean enabled) {
        T21Log.logTag = tag;
        T21Log.LOG_ENABLED = enabled;
        FileLogger.initialize(null);
    }

    /**
     * Initialize the Log to write both to Android logger and file
     *
     * @param tag:     Your appName or something You want to appear in log;
     * @param enabled: If you want to enable or disable the log
     * @param logFile: File where log will be written to
     */
    public static void initialize(String tag, boolean enabled, File logFile) {
        T21Log.logTag = tag;
        T21Log.LOG_ENABLED = enabled;
        FileLogger.initialize(logFile);
    }

    private static String getString(Object object) {
        String string = null;
        try {
            string = String.valueOf(object);
        } catch (Exception e) {
            try {
                string = object.toString();
            } catch (Exception e1) {
                android.util.Log.e(getLogTag(), e1.getMessage());
            }
            android.util.Log.e(getLogTag(), e.getMessage());
        }
        return string;
    }

    public static void v(Object... verbose) {
        if (LOG_ENABLED) {
            android.util.Log.v(getLogTag(), getLog(verbose));
            FileLogger.v(getLogTag(), getLog(verbose));
        }
    }

    public static void d(Object... debug) {
        if (LOG_ENABLED) {
            android.util.Log.d(getLogTag(), getLog(debug));
            FileLogger.d(getLogTag(), getLog(debug));
        }
    }


    public static void i(Object... info) {
        if (LOG_ENABLED) {
            android.util.Log.i(getLogTag(), getLog(info));
            FileLogger.i(getLogTag(), getLog(info));
        }
    }

    public static void w(Object... warning) {
        if (LOG_ENABLED) {
            android.util.Log.w(getLogTag(), getLog(warning));
            FileLogger.w(getLogTag(), getLog(warning));
        }
    }

    public static void e(Object... error) {
        if (LOG_ENABLED) {
            android.util.Log.e(getLogTag(), getLog(error));
            FileLogger.e(getLogTag(), getLog(error));
        }
    }

    private static String getLog(Object... objects) {
        StringBuilder sb = new StringBuilder();
        boolean space = false;
        for (Object object : objects) {
            if (space) {
                sb.append(BLANK);
            }
            sb.append(getString(object));
            space = true;
        }
        return sb.toString();
    }
}
