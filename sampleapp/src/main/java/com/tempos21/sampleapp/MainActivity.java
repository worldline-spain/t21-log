package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "[MainActivity]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        T21Log.d(TAG, "onCreate", "adding messages", 3, true, "separated by commas");

        Button sendByMailButton = (Button) findViewById(R.id.sendByMailButton);
        sendByMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File logFile = ((SampleApplication) getApplication()).getLogFile();
                Uri logFileUri = FileProvider
                        .getUriForFile(MainActivity.this, getString(R.string.file_provider_authority), logFile);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType(getString(R.string.mail_mime_type));
                emailIntent.putExtra(Intent.EXTRA_STREAM, logFileUri);
                emailIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));

                startActivity(Intent.createChooser(emailIntent, getString(R.string.mail_chooser_title)));
            }
        });

    }
}
