package com.tempos21.android.commons.utils;

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
public class T21FileLog extends Logger {

    private static final String SLASH = "/";

    private static final String COLON = ":";

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    private static final String LINE_SEPARATOR_PROPERTY = "line.separator";

    private static File logFile;

    /**
     * Initialize the Log to write both to Android logger and logFile
     *
     * @param tag:     Your appName or something You want to appear in log;
     * @param enabled: If you want to enable or disable the log
     * @param logFile: File where log will be written to
     */
    public static void initialize(String tag, boolean enabled, File logFile) {
        Logger.initialize(tag, enabled);
        T21FileLog.logFile = logFile;
    }

    private static void writeToFile(LogLevel logLevel, String logTag, String message) {
        if (logFile != null) {
            try {
                logFile.createNewFile();
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
                android.util.Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    public static void v(Object... verbose) {
        if (getLogEnabled()) {
            writeToFile(LogLevel.V, getLogTag(), getLog(verbose));
        }
    }

    public static void d(Object... debug) {
        if (getLogEnabled()) {
            writeToFile(LogLevel.D, getLogTag(), getLog(debug));
        }
    }

    public static void i(Object... info) {
        if (getLogEnabled()) {
            writeToFile(LogLevel.I, getLogTag(), getLog(info));
        }
    }

    public static void w(Object... warning) {
        if (getLogEnabled()) {
            writeToFile(LogLevel.W, getLogTag(), getLog(warning));
        }
    }

    public static void e(Object... error) {
        if (getLogEnabled()) {
            writeToFile(LogLevel.E, getLogTag(), getLog(error));
        }
    }

    private enum LogLevel {
        V, D, I, W, E
    }
}
