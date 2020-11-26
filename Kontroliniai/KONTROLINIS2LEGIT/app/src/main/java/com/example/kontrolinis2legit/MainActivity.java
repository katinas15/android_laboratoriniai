package com.example.kontrolinis2legit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    String broadcastName = "1_Programos_nesist_trans";
    String programName = "com.example.kontrolinis2legit";
    public static String channelID = "kontrolinis2";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sukurtiKanala();
    }

    public void transliuotiBroadcast(View v){
        Intent i = new Intent();
        i.setAction(broadcastName);
        i.putExtra("ProgramosPavadinimas", programName);
        i.putExtra("TrumpaZinute", "Antras Kontrolinis darbas");

        Log.i("transliavimas", "transliuojama");
        sendBroadcast(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sukurtiKanala(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = channelID;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelID, channelName, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

}