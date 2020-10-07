package com.example.kontrolinis1v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    TextView text;
    RandomNum randomGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.textView);
//        new Thread(randomGen).start();
    }

    public void start(View v){
        randomGen =  new RandomNum(100,  0, text);
        new Thread(randomGen).start();

    }

    public void stop(View v){
        randomGen.stop();
    }

    class RandomNum implements Runnable {

        AtomicBoolean running = new AtomicBoolean(false);
        int max;
        int min;
        TextView isved_lauk;
        int rand;
//        final Handler handler = new Handler();

        RandomNum(int max, int min, TextView isved_lauk) {
            this.max = max;
            this.min = min;
            this.isved_lauk = isved_lauk;
        }

        public void stop(){
            running.set(false);
        }

        public void run() {
            running.set(true);
            while (running.get()) {
                rand = (int) (Math.random() * max) + min;
                Log.i(String.valueOf(rand), "i)");
//                handler.post(new Runnable(){
//                    public void run() {
//                        isved_lauk.setText(String.valueOf(rand));
//                    }
//                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void  run(){
                        isved_lauk.setText(String.valueOf(rand));
                    }
                });
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}