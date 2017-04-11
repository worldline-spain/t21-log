package com.tempos21.android.commons.utils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class FileLoggerTest {

    private static File logFile;

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss.SSS";

    private static final String DATE_TIME_REGEX = "\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}";

    private static String TAG = "[FileLoggerTest]";

    @BeforeClass
    public static void setUpOnce() {
        logFile = new File("FileLoggerTest.log");
        FileLogger.initialize(logFile);
    }

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void verbose() {
        String VERBOSE_TAG = "V";
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(2);
        buildingTest.setName("nameTestVerbose");
        buildingTest.setAddress("addressTestVerbose");

        FileLogger.v(TAG, buildingTest.toString());

        assertTrue("Log tag '" + TAG + "' not found in log file", readFileLastLine().contains(TAG));
        assertEquals("Verbose tag '" + VERBOSE_TAG + "' not found in log file", VERBOSE_TAG, getLogLevelTag());
        assertTrue("Date and time not found in log file", getTimestamp().matches(DATE_TIME_REGEX));
        assertEquals("Object logged '" + buildingTest + "' not found in log file", buildingTest.toString(), getMessage());
    }

    @Test
    public void debug() {
        String DEBUG_TAG = "D";
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(0);
        buildingTest.setName("nameTestDebug");
        buildingTest.setAddress("addressTestDebug");

        FileLogger.d(TAG, buildingTest.toString());

        assertTrue("Log tag '" + TAG + "' not found in log file", readFileLastLine().contains(TAG));
        assertEquals("Debug tag '" + DEBUG_TAG + "' not found in log file", DEBUG_TAG, getLogLevelTag());
        assertTrue("Date and time not found in log file", getTimestamp().matches(DATE_TIME_REGEX));
        assertEquals("Object logged '" + buildingTest + "' not found in log file", buildingTest.toString(), getMessage());
    }

    @Test
    public void info() {
        String INFO_TAG = "I";
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(3);
        buildingTest.setName("nameTestInfo");
        buildingTest.setAddress("addressTestInfo");

        FileLogger.i(TAG, buildingTest.toString());

        assertTrue("Log tag '" + TAG + "' not found in log file", readFileLastLine().contains(TAG));
        assertEquals("Info tag '" + INFO_TAG + "' not found in log file", INFO_TAG, getLogLevelTag());
        assertTrue("Date and time not found in log file", getTimestamp().matches(DATE_TIME_REGEX));
        assertEquals("Object logged '" + buildingTest + "' not found in log file", buildingTest.toString(), getMessage());
    }

    @Test
    public void warning() {
        String WARNING_TAG = "W";
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(12);
        buildingTest.setName("nameTestWarning");
        buildingTest.setAddress("addressTestWarning");

        FileLogger.w(TAG, buildingTest.toString());

        assertTrue("Log tag '" + TAG + "' not found in log file", readFileLastLine().contains(TAG));
        assertEquals("Warning tag '" + WARNING_TAG + "' not found in log file", WARNING_TAG, getLogLevelTag());
        assertTrue("Date and time not found in log file", getTimestamp().matches(DATE_TIME_REGEX));
        assertEquals("Object logged '" + buildingTest + "' not found in log file", buildingTest.toString(), getMessage());
    }

    @Test
    public void error() {
        String ERROR_TAG = "E";

        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(99);
        buildingTest.setName("nameTestError");
        buildingTest.setAddress("addressTestError");

        FileLogger.e(TAG, buildingTest.toString());

        assertTrue("Log tag '" + TAG + "' not found in log file", readFileLastLine().contains(TAG));
        assertEquals("Error tag '" + ERROR_TAG + "' not found in log file", ERROR_TAG, getLogLevelTag());
        assertTrue("Date and time not found in log file", getTimestamp().matches(DATE_TIME_REGEX));
        assertEquals("Object logged '" + buildingTest + "' not found in log file", buildingTest.toString(), getMessage());
    }

    private static String getLogLevelTag() {
        String lineLogged = readFileLastLine();
        int indexOfTag = lineLogged.indexOf(TAG);
        int logLevelIndex = indexOfTag - 2;
        return lineLogged.substring(logLevelIndex, logLevelIndex + 1);
    }

    private static String getTimestamp() {
        String lineLogged = readFileLastLine();
        int indexOfTag = lineLogged.indexOf(TAG);
        int logLevelIndex = indexOfTag - 2;
        return lineLogged.substring(0, logLevelIndex).trim();
    }

    private static String getMessage() {
        String lineLogged = readFileLastLine();
        int indexOfMessage = lineLogged.indexOf(TAG) + TAG.length() + 2;
        return lineLogged.substring(indexOfMessage).trim();
    }

    private static String readFileLastLine() {
        String line = "";
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {
            String currentLine;

            bufferedReader = new BufferedReader(new FileReader(logFile));

            while ((currentLine = bufferedReader.readLine()) != null) {
                line = currentLine;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return line;
    }

    @AfterClass
    public static void tearDownOnce() {
        logFile.delete();
    }

    class BuildingTest {
        private int id;

        private String name;

        private String address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "BuildingTest{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
