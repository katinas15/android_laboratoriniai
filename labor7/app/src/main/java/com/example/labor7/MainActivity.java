package com.example.labor7;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText procentai;
    EditText laikas;
    String kanaloPav = "Kanalas1";
    int pranesimoId = 1;
    Switch alarm;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        procentai = findViewById(R.id.procentai);
        alarm = findViewById(R.id.alarm);
        laikas = findViewById(R.id.laikas);
        sukurtiKanala();

        Intent intent = new Intent(this, GautasAlarm.class);
        pendingIntent =
                PendingIntent.getBroadcast(
                        this,
                        2,
                        intent,
                        0);

    }

    public void notification(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, kanaloPav);
        builder.setSmallIcon(R.drawable.arrow);
        builder.setContentTitle("Jums senka baterija");
        builder.setContentText("Telefone yra maziau nei " + procentai.getText() + " procentu baterijos");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(pranesimoId,builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sukurtiKanala(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = kanaloPav;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(kanaloPav, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void alarmSet(View view){
        if(alarm.isChecked()){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            long periodas = Long.parseLong(String.valueOf(laikas.getText()));
            Log.i("a", "paspausta " + periodas);
            alarmManager.setRepeating(
                    AlarmManager.RTC,
                    System.currentTimeMillis() + periodas,
                    periodas,
                    pendingIntent);
            Log.i("a", "nustatyta");

        } else {
            if (pendingIntent != null && alarmManager != null) {
                Log.i("a", "alarm canceled");
                alarmManager.cancel(pendingIntent);
            }
        }


    }
}