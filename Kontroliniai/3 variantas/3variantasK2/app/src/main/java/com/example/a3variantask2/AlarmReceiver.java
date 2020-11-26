package com.example.a3variantask2;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {

    int notificationId = 1;
    static boolean isAlarmPlaying = false;
    static MediaPlayer mp;
    Uri alarmSound;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("a", "gautaw alarmas");
        sendNotification(context);
    }

    public void sendNotification(Context context) {
        PlayAlarmSound(context);
        WakeScreen(context);

        Log.i("a", "siunciamas notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), MainActivity.channelID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("Atejo numaryras laikas ");
        builder.setContentText("Nustatytas laiaks buvo - " + MainActivity.startTime);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context.getApplicationContext(), NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(notificationId, builder.build());
    }

    private void WakeScreen(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = Build.VERSION.SDK_INT >= 20 ? pm.isInteractive() : pm.isScreenOn(); // check if screen is on
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "myApp:notificationLock");
            wl.acquire(3000); //set your time in milliseconds
        }
    }

    public void PlayAlarmSound(Context context){
        if(!isAlarmPlaying){
            isAlarmPlaying = true;
            alarmSound = RingtoneManager. getDefaultUri (RingtoneManager.TYPE_ALARM );
            mp = MediaPlayer.create(context.getApplicationContext(), alarmSound);
            mp.start();
        }
    }

    public static void StopAlarmSound(){
        isAlarmPlaying = false;
        mp.stop();
    }


}