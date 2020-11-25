package com.example.labor5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static FragmentManager FM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FM = getSupportFragmentManager();
    }

    public static void suA(String string){
        Bundle bundle = new Bundle();
        bundle.putString("text", string);
        SuA fragment = new SuA();
        fragment.setArguments(bundle);
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.frame, fragment);
        FT.addToBackStack("back");
        FT.commit();
    }

    public static void beA(String string){
        Bundle bundle = new Bundle();
        bundle.putString("text", string);
        BeA fragment = new BeA();
        fragment.setArguments(bundle);
        FragmentTransaction FT = FM.beginTransaction();
        FT.add(R.id.frame, fragment);
        FT.addToBackStack("back");
        FT.commit();
    }
}