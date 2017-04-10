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
 * Class that writes log messages to a file.
 */
class FileLogger {

    private static final String SLASH = "/";

    private static final String COLON = ":";

    private static final String BLANK = " ";

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    private static File file;

    /**
     * Initialize the FileLogger
     *
     * @param file: File where you want the log to be written
     */
    public static void initialize(File file) {
        FileLogger.file = file;
    }

    private static void writeToFile(LogLevel logLevel, String logTag, String message) {
        if (file != null) {
            try {
                file.createNewFile();
                FileOutputStream fileOutStream = new FileOutputStream(file, true);
                OutputStreamWriter outStreamWriter = new OutputStreamWriter(fileOutStream);

                //Example of line in file:
                //2017/04/02 19:44:55 V/FileLoggerTag: message logged
                StringBuilder sb = new StringBuilder();
                Calendar now = Calendar.getInstance();
                DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault());
                sb.append(formatter.format(now.getTime()));
                sb.append(BLANK);
                sb.append(logLevel);
                sb.append(SLASH);
                sb.append(logTag);
                sb.append(COLON);
                sb.append(BLANK);
                sb.append(message);
                sb.append(System.getProperty("line.separator"));

                outStreamWriter.append(sb.toString());

                outStreamWriter.close();

                fileOutStream.flush();
                fileOutStream.close();
            } catch (IOException e) {
                android.util.Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }

    public static void v(String logTag, String message) {
        writeToFile(LogLevel.V, logTag, message);
    }

    public static void d(String logTag, String message) {
        writeToFile(LogLevel.D, logTag, message);
    }

    public static void i(String logTag, String message) {
        writeToFile(LogLevel.I, logTag, message);
    }

    public static void w(String logTag, String message) {
        writeToFile(LogLevel.W, logTag, message);
    }

    public static void e(String logTag, String message) {
        writeToFile(LogLevel.E, logTag, message);
    }

    private enum LogLevel {
        V, D, I, W, E
    }
}
