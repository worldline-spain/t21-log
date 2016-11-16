package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "[MainActivity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T21Log.d(TAG, "onCreate", "adding messages", 3, true, "separated by commas");

    }
}
