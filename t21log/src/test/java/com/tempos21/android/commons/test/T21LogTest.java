package com.tempos21.android.commons.test;

import org.junit.Test;

public class T21LogTest {

    private static String getString(Object object) {
        String string = null;
        try {
            string = String.valueOf(object);
        } catch (Exception e) {
            try {
                string = object.toString();
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            System.out.println(e.getMessage());
        }
        return string;
    }

    private static void v(Object... verbose) {
        if (true) {
            StringBuilder sb = new StringBuilder();
            for (Object object : verbose) {
                sb.append(getString(object));
            }
            //            android.util.Log.v(getLogTag(), sb.toString());
            System.out.println(sb.toString());
        }
    }

    private static void d(Object... verbose) {
        if (true) {
            StringBuilder sb = new StringBuilder();
            for (Object object : verbose) {
                sb.append(getString(object));
            }
            //            android.util.Log.v(getLogTag(), sb.toString());
            System.out.println(sb.toString());
        }
    }

    @Test
    public void verbose() {
        int i = 0;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(2);
        buildingTest.setName("nameTest");
        buildingTest.setAddress("addressTest");
        v("hola: ", i, " :: ", buildingTest);
    }

    @Test
    public void debug() {
        int i = 0;
        BuildingTest buildingTest = new BuildingTest();
        buildingTest.setId(2);
        buildingTest.setName("nameTest");
        buildingTest.setAddress("addressTest");
        d("hola: ", i, " :: ", buildingTest);
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
