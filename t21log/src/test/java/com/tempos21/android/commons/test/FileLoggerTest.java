package com.tempos21.android.commons.test;

import com.tempos21.android.commons.utils.FileLogger;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class FileLoggerTest {

    private static File logFile;

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

        checkLogFile(VERBOSE_TAG, buildingTest);
    }

    @Test
    public void debug() {
        String DEBUG_TAG = "D";

        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(0);
        buildingTest.setName("nameTestDebug");
        buildingTest.setAddress("addressTestDebug");
        FileLogger.d(TAG, buildingTest.toString());

        checkLogFile(DEBUG_TAG, buildingTest);
    }

    @Test
    public void info() {
        String INFO_TAG = "I";

        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(3);
        buildingTest.setName("nameTestInfo");
        buildingTest.setAddress("addressTestInfo");
        FileLogger.i(TAG, buildingTest.toString());

        checkLogFile(INFO_TAG, buildingTest);
    }

    @Test
    public void warning() {
        String WARNING_TAG = "W";

        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(12);
        buildingTest.setName("nameTestWarning");
        buildingTest.setAddress("addressTestWarning");
        FileLogger.w(TAG, buildingTest.toString());

        checkLogFile(WARNING_TAG, buildingTest);
    }

    @Test
    public void error() {
        String ERROR_TAG = "E";

        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(99);
        buildingTest.setName("nameTestError");
        buildingTest.setAddress("addressTestError");
        FileLogger.e(TAG, buildingTest.toString());

        checkLogFile(ERROR_TAG, buildingTest);
    }

    private static void checkLogFile(String logLevelTag, BuildingTest buildingTest) {
        String lineLogged = readFileLastLine();

        int indexOfTag = lineLogged.indexOf(TAG);
        int logLevelIndex = indexOfTag - 2;

        assertTrue("Verbose tag '" + logLevelTag + "' not found in log file",
                lineLogged.substring(logLevelIndex, indexOfTag - 1).equals(logLevelTag));
        assertTrue("Log tag '" + TAG + "' not found in log file", lineLogged.contains(TAG));

        String dateTimeString = lineLogged.substring(0, logLevelIndex);
        DateFormat formatter = SimpleDateFormat.getDateTimeInstance();
        try {
            formatter.parse(dateTimeString);
        } catch (ParseException e) {
            fail("Date and time not found in log file");
        }

        assertTrue("Object logged '" + buildingTest + "' not found in log file", lineLogged.contains(buildingTest.toString()));
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
