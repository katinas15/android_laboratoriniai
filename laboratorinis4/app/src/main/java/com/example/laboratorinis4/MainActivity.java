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
//        puslapis.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, android.os.Message resultMsg)
//            {
//                WebView.HitTestResult result = view.getHitTestResult();
//                String data = result.getExtra();
//                Context context = view.getContext();
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(data));
//                context.startActivity(browserIntent);
//                return false;
//            }
//        });
//        puslapis.loadUrl("https://www.google.com");
    }

    public void changeWeb(View v){

//        puslapis = (WebView) findViewById(R.id.webview);
        puslapis.loadUrl(link.getText().toString());
    }
}