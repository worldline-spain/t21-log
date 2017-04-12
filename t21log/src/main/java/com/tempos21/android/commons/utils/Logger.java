package com.tempos21.android.commons.utils;

/**
 * Abstract class that all Loggers must extend.
 */
abstract class Logger {

    private static final String BLANK = " ";

    private static boolean LOG_ENABLED = BuildConfig.LOG_ENABLED;

    private static String logTag = null;

    /**
     * Initialize the Log
     *
     * @param tag:     Your appName or something You want to appear in log;
     * @param enabled: If you want to enable or disable the log
     */
    static void initialize(String tag, boolean enabled) {
        Logger.logTag = tag;
        Logger.LOG_ENABLED = enabled;
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

    static String getLogTag() {
        if (logTag == null) {
            return (T21Log.class.getPackage().getName());
        }
        return logTag;
    }

    static String getLog(Object... objects) {
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

    static boolean getLogEnabled() {
        return LOG_ENABLED;
    }

    static String getBlank() {
        return BLANK;
    }
}
