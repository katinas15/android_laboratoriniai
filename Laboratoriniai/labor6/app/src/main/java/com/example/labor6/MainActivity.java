package com.example.labor6;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView pirmas;
    TextView antras;
    TextView trecias;
    Context context = this;
    String currentText;
    TextThread t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pirmas = findViewById(R.id.pirmas);
        antras = findViewById(R.id.antras);
        trecias = findViewById(R.id.trecias);

        registerForContextMenu(pirmas);
        registerForContextMenu(antras);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        currentText = ( (TextView) v ).getText().toString();
        getMenuInflater().inflate(R.menu.click_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sk:
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Alert");
                alert.setMessage("Simboliu sk. - " + currentText.length());

                alert.create().show();

                antras.setText("radau " + currentText.length() + " simboliu");
                return true;
            case R.id.ra:
                t = new TextThread();
                t.start();
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.submeniu) {
            Log.i("log", "CLICKED");
            Calendar calendar = Calendar.getInstance();
            final int hour = calendar.get(Calendar.HOUR_OF_DAY);
            final int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int i, int i1) {
                            Log.i("log", String.valueOf(i + ':' + i1));
                            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                            Date time = new Date();
                            Date date1 = null;
                            Date date2 = null;
                            try {
                                date1 = format.parse( time.getHours() + ":" + time.getMinutes());
                                date2 = format.parse(i + ":" + i1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            long difference = date2.getTime() - date1.getTime();
                            AlertDialog.Builder alert = new AlertDialog.Builder(context);
                                alert.setTitle("Alert");
                                alert.setMessage("skirtumas tarp laiko - " + difference/1000/60 + "min");

                            alert.create().show();

                            pirmas.setText("skirtumas tarp laiko - " + difference/1000/60 + "min");
                        }
                    }, hour, minute, DateFormat.is24HourFormat(context));
            timePickerDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    class TextThread extends Thread{
        TextThread() {}

        public void run() {
            int i = 0;
            while(true){
                if(i == currentText.length()) return;
                setText(trecias, currentText.substring(i, i+1));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    i++;
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }


    public void close(MenuItem item){
        finishAffinity();
        System.exit(0);
    }

}