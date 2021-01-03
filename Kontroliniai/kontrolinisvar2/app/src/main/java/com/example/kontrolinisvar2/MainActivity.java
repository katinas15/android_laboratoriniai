package com.example.kontrolinisvar2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static String manoKodas = "koderrr";
    public String md5Kodas;
    public static String link = "http://md5.jsontest.com/";
    String channelName = "black";
    TextView textView;
    Button button;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text1);
        button = findViewById(R.id.button3);
        button.setVisibility(View.INVISIBLE);
        createChannel();
//        irasyti(md5Kodas);
    }

    public void siustiUzklausa(View v){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, link + "?text=" + manoKodas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            final String md5 = jsonObject.getString("md5");
                            if(md5Kodas.equals(md5)){
                                textView.setText("md5 nepasikeite: " + md5);
                            } else {
                                textView.setText("PASIKEITE: " + md5 + "   " + md5Kodas);
                                button.setVisibility(View.VISIBLE);
                                notification();
                                button.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View v) {
                                        irasyti(md5);
                                        button.setVisibility(View.INVISIBLE);
                                        textView.setText("IRASYTAS NAUJAS KODAS " + md5);
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //textView.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!" + error.toString());
                Log.i("klaida", "That didn't work!" + error.toString());
            }
        });
        queue.add(stringRequest);
    }


    public void siustiUzklausaSocket(View v){
        new Thread(new Runnable() {
            public void run() {

                try {
                    String hostname = "md5.jsontest.com";
                    int port = 80;

                    InetAddress addr = InetAddress.getByName(hostname);
                    Socket socket = new Socket(addr, port);

                    // Send headers
                    BufferedWriter wr =
                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
                    wr.write("GET http://" + hostname + "?text=" + manoKodas + "\r\n");
                    wr.write("Host: " + hostname + "\r\n");

                    wr.flush();

                    // Get response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;

                    final String response = "";
                    System.out.println(response);
                    String responseJSON = "";
                    boolean startedResponse = false;
                    while ((line = rd.readLine()) != null) {
                        if(line.equals("{")) startedResponse = true;
                        if(startedResponse) responseJSON += line;


                        Log.d("tagas", line);
                        response.concat(line + "\n");
                    }

                    final String lineToShow = responseJSON;
                    Log.d("tagas", responseJSON);

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            //textView.setText(lineToShow);
                            try {
                                JSONObject jsonObject = new JSONObject(lineToShow);
                                final String md5 = jsonObject.getString("md5");
                                if(md5Kodas.equals(md5)){
                                    textView.setText("md5 nepasikeite: " + md5);
                                } else {
                                    textView.setText("PASIKEITE: " + md5 + "   " + md5Kodas);
                                    button.setVisibility(View.VISIBLE);
                                    notification();
                                    button.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            irasyti(md5);
                                            button.setVisibility(View.INVISIBLE);
                                            textView.setText("IRASYTAS NAUJAS KODAS " + md5);
                                        }
                                    });
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    wr.close();
                    rd.close();
                } catch (Exception e) {
                    Log.d("tagas", e.toString());
                }
            }
        }).start();
    }

    public void irasyti(String kodas) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("md5", kodas);
        editor.apply();
    }

    public void gauti(View v){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        textView.setText( sharedPref.getString("md5", "no value"));
        md5Kodas = sharedPref.getString("md5", "no value");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = channelName;

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelName, name, importance);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelName);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("HELO");
        builder.setContentText("DUOMENYS NESUTAMPA");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(1, builder.build());
    }
}