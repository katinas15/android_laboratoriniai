package com.example.laboratorinis4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView puslapis;
    EditText link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link = (EditText) findViewById(R.id.editText);

        puslapis = (WebView) findViewById(R.id.webview);

        puslapis.getSettings().setJavaScriptEnabled(true);
        puslapis.setWebViewClient(new WebViewClient());

    }

    public void changeWeb(View v){

        puslapis.loadUrl(link.getText().toString());
    }
}