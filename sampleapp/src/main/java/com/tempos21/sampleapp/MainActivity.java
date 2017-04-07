package com.tempos21.sampleapp;

import com.tempos21.android.commons.utils.T21Log;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "[MainActivity]";

    private Button sendByMailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendByMailButton = (Button) findViewById(R.id.sendByMailButton);
        sendByMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File logFile = ((SampleApplication) getApplication()).getLogFile();
                Uri path = Uri.fromFile(logFile);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // set the type to 'email'
                emailIntent.setType("message/rfc822");
                // the attachment
                emailIntent.putExtra(Intent.EXTRA_STREAM, path);
                // the mail subject
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
                startActivity(Intent.createChooser(emailIntent, getString(R.string.mail_chooser_title)));
            }
        });
        T21Log.d(TAG, "onCreate", "adding messages", 3, true, "separated by commas");

    }
}
