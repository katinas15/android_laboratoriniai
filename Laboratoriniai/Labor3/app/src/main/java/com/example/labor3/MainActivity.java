package com.example.labor3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                fakultetai);

        antras.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapterSp = ArrayAdapter.createFromResource(this,
                R.array.dienos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        ketvirtas.setAdapter(adapterSp);
    }

    final static String[] fakultetai = new String[] {
            "AIF - Aplinkos inžinerijos fakultetas", "AF - Architektūros fakultetas", "EF - Elektronikos fakultetas", "FMF - Fundamentinių mokslų fakultetas", "KIF - Kūrybinių industrijų fakultetas", "MF - Mechanikos fakultetas", "SF -Statybos fakultetas", "TIF - Transporto inžinerijos fakultetas", "VVF - Verslo vadybos fakultetas"
    };


    public void toastas(View v){
        String tekstas = "";
        tekstas += pirmas.getText().toString() + '\n';
        tekstas += antras.getText().toString() + '\n';
        tekstas += String.valueOf(trecias.getRating()) + '\n';
        tekstas += ketvirtas.getSelectedItem().toString() + '\n';
        tekstas += penktas.getText().toString() + '\n';
        tekstas += String.valueOf(sestas.isChecked()) + '\n';
        Toast.makeText(MainActivity.this, tekstas, Toast.LENGTH_LONG).show();
    }
}