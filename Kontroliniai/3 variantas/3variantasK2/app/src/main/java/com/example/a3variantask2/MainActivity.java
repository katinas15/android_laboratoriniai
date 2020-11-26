package com.example.a3variantask2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    AlarmManager alarmManager;
    public static long period = 1000 * 60;
    public static String startTime;
    public static String channelID = "kontrolinis2";
    boolean isAlarmSet = false;

    PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sukurtiKanala();

        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        2,
                        intent,
                        0);
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

    public void toggleAlarm(View view){
        if(!isAlarmSet){

            Calendar mainCalendar = Calendar.getInstance();
            Log.i("dabartinis laikas", String.valueOf(mainCalendar.getTime()));

            Calendar cloneCalendar = (Calendar) mainCalendar.clone();
            cloneCalendar.add(Calendar.MINUTE, 1);
            startTime = String.valueOf(cloneCalendar.getTime());
            Log.i("nustatytas laikas", startTime);
            long notificationTime = cloneCalendar.getTimeInMillis();

//            Calendar specificTimeCalendar = (Calendar) mainCalendar.clone();
//            specificTimeCalendar.set(Calendar.HOUR_OF_DAY, 11);
//            specificTimeCalendar.set(Calendar.MINUTE, 57);
//            specificTimeCalendar.set(Calendar.SECOND, 0);
//            startTime = String.valueOf(specificTimeCalendar.getTime());
//            Log.i("nustatytas laikas", startTime);
//            long notificationTime = specificTimeCalendar.getTimeInMillis();

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Log.i("a", "setting alarm  " + period);
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    notificationTime,
                    period,
                    pendingIntent);

            isAlarmSet = true;
            Log.i("a", "alarm set");

        } else {
            if (pendingIntent != null && alarmManager != null) {
                Log.i("a", "alarm canceled");
                isAlarmSet = false;
                alarmManager.cancel(pendingIntent);
            }
        }
    }

}