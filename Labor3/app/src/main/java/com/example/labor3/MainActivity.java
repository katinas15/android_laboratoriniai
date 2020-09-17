package com.example.labor3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText pirmas;
    AutoCompleteTextView antras;
    RatingBar trecias;
    Spinner ketvirtas;
    EditText penktas;
    Switch sestas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pirmas = (EditText) findViewById(R.id.pirmas);
        antras = (AutoCompleteTextView) findViewById(R.id.antras);
        trecias = (RatingBar) findViewById(R.id.trecias);
        ketvirtas = (Spinner) findViewById(R.id.ketvirtas);
        penktas = (EditText) findViewById(R.id.penktas);
        sestas = (Switch) findViewById(R.id.sestas);
    }


    public void toastas(){
        String tekstas = "";
        tekstas += pirmas.getText().toString() + '\n';
        tekstas += antras.getText().toString() + '\n';
        tekstas += trecias.getNumStars() + '\n';
        tekstas += ketvirtas.get.toString() + '\n';
        tekstas += antras.getText().toString() + '\n';
        tekstas += antras.getText().toString() + '\n';
        Toast.makeText(MainActivity.this, "Error! Bad login or password", Toast.LENGTH_LONG).show();
    }
}