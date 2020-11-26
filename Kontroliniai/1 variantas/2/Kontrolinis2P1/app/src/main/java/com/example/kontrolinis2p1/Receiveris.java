package com.example.kontrolinis2p1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Receiveris extends BroadcastReceiver {

    static MainActivity mainActivity;

    @Override
    public void onReceive(Context context, Intent i) {

        TextView l  = MainActivity.mainActivity.findViewById(R.id.textView);
        l.setText(i.getExtras().getString("Message"));

    }
}