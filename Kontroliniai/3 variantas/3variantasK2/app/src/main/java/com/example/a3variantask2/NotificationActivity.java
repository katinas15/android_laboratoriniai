package com.example.a3variantask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import static android.media.MediaPlayer.*;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void StopAlarmSound(View v){
        AlarmReceiver.StopAlarmSound();
    }

    public void ExitProgram(View v){
        finishAffinity();
        System.exit(0);
    }


}