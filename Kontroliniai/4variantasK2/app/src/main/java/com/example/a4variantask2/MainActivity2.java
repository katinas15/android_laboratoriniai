package com.example.a4variantask2;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    static FragmentManager FM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FM = getSupportFragmentManager();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.pirmas) {
            ButtonFragment1 fragment = new ButtonFragment1();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.frame, fragment);
//            FT.addToBackStack("back");
            FT.commit();

        } else if (id == R.id.antras){

            ButtonFragment2 fragment = new ButtonFragment2();
            FragmentTransaction FT = FM.beginTransaction();
            FT.add(R.id.frame, fragment);
//            FT.addToBackStack("back");
            FT.commit();

        }

        return super.onOptionsItemSelected(item);
    }

}