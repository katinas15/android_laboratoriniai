package com.example.kontrolinis2p1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;
        Receiveris.mainActivity = this;

        IntentFilter filtras = new IntentFilter();
        filtras.addAction("2_Programos_nesist_isreikst_trans");
        registerReceiver(new Receiveris(), filtras);
    }

    public void broadcast(View v){
        Intent i = new Intent();
        i.setAction("1_Programos_nesist_trans");
        i.putExtra("AppName", "com.example.kontrolinis2p1");
        i.putExtra("Message", "Very Nice");
        sendBroadcast(i);

    }


}