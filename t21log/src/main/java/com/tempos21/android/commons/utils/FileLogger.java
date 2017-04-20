package com.tempos21.android.commons.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class that writes log messages to a logFile.
 */
class FileLogger extends Logger {

    private static final String T21_LOG_TAG = "T21Log";

    private static final String SLASH = "/";

    private static final String COLON = ":";

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    private static final String LINE_SEPARATOR_PROPERTY = "line.separator";

    private static final String LOG_PATH = "logs";

    private static final String LOG_FILE_NAME = "T21Log.log";

    private static File logFile;

    private static boolean ENABLED = false;

    /**
     * Initialize the FileLogger to write to a file.
     *
     * @param tag:     Your appName or something you want to appear in log
     * @param enabled: If you want to enable or disable the log
     * @param context: Android app context
     */
    static void initialize(String tag, boolean enabled, Context context) {
        Logger.initialize(tag);
        ENABLED = enabled;
        File logsPath = new File(context.getFilesDir(), LOG_PATH);
        if (logsPath.exists() || (!logsPath.exists() && logsPath.mkdir())) {
            logFile = new File(logsPath, LOG_FILE_NAME);

            try {
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }
            } catch (IOException e) {
                ENABLED = false;
                android.util.Log
                        .e(T21_LOG_TAG, "Unable to create log file '" + logFile.getName() + "'. Logging to file disabled.\n" + e);
            }
        } else {
            ENABLED = false;
            android.util.Log.e(T21_LOG_TAG, "Unable to create 'logs' directory. Logging to file disabled");
        }
    }

    static File getLogFile() {
        return logFile;
    }

    private static void writeToFile(LogLevel logLevel, String logTag, String message) {
        if (logFile != null) {
            try {
                FileOutputStream fileOutStream = new FileOutputStream(logFile, true);
                OutputStreamWriter outStreamWriter = new OutputStreamWriter(fileOutStream);

                StringBuilder sb = new StringBuilder();
                Calendar now = Calendar.getInstance();
                DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
                sb.append(formatter.format(now.getTime()));
                sb.append(getBlank());
                sb.append(logLevel);
                sb.append(SLASH);
                sb.append(logTag);
                sb.append(COLON);
                sb.append(getBlank());
                sb.append(message);
                sb.append(System.getProperty(LINE_SEPARATOR_PROPERTY));

                outStreamWriter.append(sb.toString());

                outStreamWriter.close();

                fileOutStream.flush();
                fileOutStream.close();
            } catch (IOException e) {
                android.util.Log.e(T21_LOG_TAG, "File write failed: " + e.toString());
            }
        }
    }

    static void v(Object... verbose) {
        if (ENABLED) {
            writeToFile(LogLevel.V, getLogTag(), getLog(verbose));
        }
    }

    static void d(Object... debug) {
        if (ENABLED) {
            writeToFile(LogLevel.D, getLogTag(), getLog(debug));
        }
    }

    static void i(Object... info) {
        if (ENABLED) {
            writeToFile(LogLevel.I, getLogTag(), getLog(info));
        }
    }

    static void w(Object... warning) {
        if (ENABLED) {
            writeToFile(LogLevel.W, getLogTag(), getLog(warning));
        }
    }

    static void e(Object... error) {
        if (ENABLED) {
            writeToFile(LogLevel.E, getLogTag(), getLog(error));
        }
    }

    private enum LogLevel {
        V, D, I, W, E
    }
}
