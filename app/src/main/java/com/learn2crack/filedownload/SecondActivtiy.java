package com.learn2crack.filedownload;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.learn2crack.filedownload.models.Download;

public class SecondActivtiy extends AppCompatActivity {

    public static final String MESSAGE_PROGRESS = "message_progress";
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activtiy);

        registerReceiver();

        mProgressBar=(ProgressBar)findViewById(R.id.progress);
    }

    private void registerReceiver(){

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getAction().equals(MESSAGE_PROGRESS)){

                Download download = intent.getParcelableExtra("download");
                mProgressBar.setProgress(download.getProgress());
                if(download.getProgress() == 100){

                    //mProgressText.setText("File Download Complete");

                } else {

                    //mProgressText.setText(String.format("Downloaded (%d/%d) MB",download.getCurrentFileSize(),download.getTotalFileSize()));

                }
            }
        }
    };
}
