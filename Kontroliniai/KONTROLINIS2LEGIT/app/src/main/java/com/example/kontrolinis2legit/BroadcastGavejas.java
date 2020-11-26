package com.example.kontrolinis2legit;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class BroadcastGavejas extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("gautas", "gautas receive is kito broadcast");


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context.getApplicationContext(), MainActivity.channelID);
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setContentTitle("GAUTAS PRANESIMAS ");
        builder.setContentText(intent.getExtras().getString("Message"));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);


        PendingIntent pendingIntent =
                PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(1, builder.build());
    }
}
