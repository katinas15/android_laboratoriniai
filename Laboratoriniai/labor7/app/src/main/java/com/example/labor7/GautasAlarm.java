package com.example.labor7;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class GautasAlarm extends BroadcastReceiver {

    String kanaloPav = "Kanalas1";
    int pranesimoId = 1;
    float batteryPct;
    @Override
    public void onReceive(Context context, Intent intent){
        Log.i("a","gautas");
//        EditText procentai = (EditText) ((Activity) context.getApplicationContext()).findViewById(R.id.procentai);
//        int ivestiProcentai = Integer.parseInt(String.valueOf(procentai.getText()));
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);


        batteryPct = level * 100 / (float)scale;
        Log.i("a", "baterija " + batteryPct);
        if(batteryPct <= 20) {
            notification(context);
        }
    }

    public void notification(Context context){
        Log.i("a", "siunciamas notification");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), kanaloPav);
        builder.setSmallIcon(R.drawable.arrow);
        builder.setContentTitle("Jums senka baterija, maziau nei 20 procentu baterijos");
        builder.setContentText("Telefone yra " + batteryPct + " procentu baterijos");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context.getApplicationContext(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context.getApplicationContext());
        manager.notify(pranesimoId,builder.build());
    }

}
