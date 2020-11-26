package com.example.kontrolinis2v2v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String broadcastName1 = "1_Programos_nesist_trans";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tekstinisLaukas);

        IntentFilter filtras = new IntentFilter(broadcastName1);
        registerReceiver(new BroadcastImtuvas(), filtras);
    }

    public class BroadcastImtuvas extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent i) {
            String pranesimas = "Gauta naujas transliavimo pranesimas \n";
            pranesimas += "Intent: " + i.getAction() + "\n";
            pranesimas += "Siuntejas: " + i.getExtras().getString("ProgramosPavadinimas") + "\n";
            pranesimas += "Papildoma: " + i.getExtras().getString("TrumpaZinute") + "\n";

            textView.setText(pranesimas);


//            PackageManager packageManager = getPackageManager();
//            List<ResolveInfo> infos = packageManager.queryBroadcastReceivers(intent, 0);
//            for (ResolveInfo info : infos) {
//                ComponentName cn = new ComponentName(info.activityInfo.packageName,
//                        info.activityInfo.name);
//                intent.setComponent(cn);
//                sendBroadcast(intent);
//            }
//https://codinginflow.com/tutorials/android/broadcastreceiver/part-4-explicit-broadcasts


            Log.i("recevied 2", "Gautas pranesimas ir siunciamas pranesimas atgal");
            Intent intent = new Intent("2_Programos_nesist_isreikst_trans");
            intent.setAction("2_Programos_nesist_isreikst_trans");
            intent.putExtra("Message",
                    "Broadcast gautas, kurio pavadinimas yra - " + broadcastName1);

            ComponentName componentName =
                    new ComponentName("com.example.kontrolinis2legit","com.example.kontrolinis2legit.BroadcastGavejas");
            intent.setComponent(componentName);
            sendBroadcast(intent);

        }
    }


}