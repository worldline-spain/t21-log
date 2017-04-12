package com.tempos21.android.commons.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import android.util.Log;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class T21LogTest {

    private static String TAG = "[T21LogTest]";

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Log.class);
        T21Log.initialize(TAG, true);
    }

    @Test
    public void verbose() {
        int i = 0;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(2);
        buildingTest.setName("nameTest");
        buildingTest.setAddress("addressTest");

        T21Log.v("hola: ", i, " :: ", buildingTest);

        verifyStatic(times(1));
        Log.v(eq(TAG), anyString());
    }

    @Test
    public void debug() {
        int i = 56;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(2);
        buildingTest.setName("nameTest");
        buildingTest.setAddress("addressTest");

        T21Log.d("hola: ", i, " :: ", buildingTest);

        verifyStatic(times(1));
        Log.d(eq(TAG), anyString());
    }

    @Test
    public void info() {
        int i = 787;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(3);
        buildingTest.setName("nameTestInfo");
        buildingTest.setAddress("addressTestInfo");

        T21Log.i("hola: ", i, " :: ", buildingTest);

        verifyStatic(times(1));
        Log.i(eq(TAG), anyString());
    }

    @Test
    public void warning() {
        int i = 1;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(12);
        buildingTest.setName("nameTestWarning");
        buildingTest.setAddress("addressTestWarning");

        T21Log.w("hola: ", i, " :: ", buildingTest);

        verifyStatic(times(1));
        Log.w(eq(TAG), anyString());
    }

    @Test
    public void error() {
        int i = -56;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(99);
        buildingTest.setName("nameTestError");
        buildingTest.setAddress("addressTestError");

        T21Log.e("hola: ", i, " :: ", buildingTest);

        verifyStatic(times(1));
        Log.e(eq(TAG), anyString());
    }

    @Test
    public void disabledLog() {
        T21Log.initialize(TAG, false);
        int i = -1;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(44);
        buildingTest.setName("nameTestDisabled");
        buildingTest.setAddress("addressTestDisabled");

        T21Log.v("hola: ", i, " :: ", buildingTest);

        verifyStatic(never());
        Log.v(eq(TAG), anyString());
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
