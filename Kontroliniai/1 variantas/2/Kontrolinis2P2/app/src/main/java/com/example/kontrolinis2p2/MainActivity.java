package com.example.kontrolinis2p2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String broadcastName = "1_Programos_nesist_trans";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        IntentFilter filtras = new IntentFilter(broadcastName);
        registerReceiver(new Transl_Imtuvas_1(), filtras);
    }

    public class Transl_Imtuvas_1 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent i) {

            Bundle extras = i.getExtras();

            String pranesimas = "Gauta naujas transliavimo pranesimas \n";
            pranesimas += "Intent: " + i.getAction() + "\n";
            pranesimas += "Siuntejas: " + extras.getString("AppName") + "\n";
            pranesimas += "Papildoma: " + extras.getString("Message") + "\n";


            textView.setText(pranesimas);
            broadcastIntent();
        }
    }

    private void broadcastIntent() {
//        Log.i("a", "gautas panesimas, siunciama atgal");
//        Intent intent = new Intent();
//        intent.setAction("2_Programos_nesist_isreikst_trans");
//        intent.putExtra("Message", "Broadcast gautas, kurio pavadinimas yra - " + broadcastName);
//        PackageManager packageManager = getPackageManager();
//        List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(intent, 0);
//        for (ResolveInfo info : infos) {
//            ComponentName cn = new ComponentName(info.activityInfo.packageName,
//                    info.activityInfo.name);
//            intent.setComponent(cn);
//            sendBroadcast(intent);
//            Log.i("a","siunciama");
//        }


        Intent intent = new Intent("2_Programos_nesist_isreikst_trans");
        ComponentName cn1 = new ComponentName(senderLocation,
                senderLocation+replyTo);
        intent.setComponent(cn1);
        MainActivity.getInstance().sendBroadcast(intent);


    }

}