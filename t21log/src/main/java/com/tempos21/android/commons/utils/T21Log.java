package com.tempos21.android.commons.utils;

import android.content.Context;

import java.io.File;

public class T21Log {

    /**
     * Initialize the FileLogger to write to a file.
     *
     * @param tag:           Your appName or something you want to appear in log
     * @param enableConsole: If you want to enable or disable the Android console log
     * @param enableFile:    If you want to enable or disable the file log
     * @param context:       Your app context
     */
    public static void initialize(String tag, boolean enableConsole, boolean enableFile, Context context) {
        AndroidConsoleLogger.initialize(tag, enableConsole);
        FileLogger.initialize(tag, enableFile, context);
    }

    public static void v(Object... verbose) {
        AndroidConsoleLogger.v(verbose);
        FileLogger.v(verbose);
    }

    public static void d(Object... debug) {
        AndroidConsoleLogger.d(debug);
        FileLogger.d(debug);
    }

    public static void i(Object... info) {
        AndroidConsoleLogger.i(info);
        FileLogger.i(info);
    }

    public static void w(Object... warning) {
        AndroidConsoleLogger.w(warning);
        FileLogger.w(warning);
    }

    public static void e(Object... error) {
        AndroidConsoleLogger.e(error);
        FileLogger.e(error);
    }

    /**
     * Returns the file where the log is written.
     *
     * @return If file log has been enabled, returns the file where the log is written. Otherwise, returns null.
     */
    public static File getLogFile() {
        return FileLogger.getLogFile();
    }
}
