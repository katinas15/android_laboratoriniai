package com.example.kontrolinis1pvz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView text;
    RandomThread r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
    }

    public void random(View v){
        int min = 0;
        int max = 100;
        r = new RandomThread(min,max);
        r.start();
    }

    public void stop(View v){
        r.interrupt();
    }

    private void setText(final TextView text,final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }

    public void NumberSearch(String number){
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, number);
        startActivity(intent);
    }

    class RandomThread extends Thread{
        int min;
        int max;
        RandomThread(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public void run() {
            Random rand = new Random();
            while(true){
                int number = rand.nextInt(max) + min;
                setText(text, String.valueOf(number));
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    NumberSearch(String.valueOf(number));
                    return;
                }
            }
        }
    }
}