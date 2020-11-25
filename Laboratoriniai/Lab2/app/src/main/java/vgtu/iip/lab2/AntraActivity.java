package vgtu.iip.lab2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AntraActivity extends AppCompatActivity {

    EditText ivedimoLaukas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antra);
        ivedimoLaukas = (EditText) findViewById(R.id.ivedimui);
        Log.i("Veiksmas", "Atidaromas ivesties langas");
    }

    public void grazintiRezultatus(View w){
//        Log.i("TODO", "Realizuoti rezultatu grazinima");
        Log.i("Veiksmas", "Spaudziama saugoti ir grazinami rezultatai");
        String ivestasTekstas = ivedimoLaukas.getText().toString();
        //TODO sukurti intent, nurodant papildomai grazinamus duomenis ir nustatant, kad rezultatas yra OK
        Intent PirmasLangas = new Intent(AntraActivity.this, PirmaActivity.class);
        PirmasLangas.putExtra("ivestis", ivestasTekstas);

        setResult(Activity.RESULT_OK, PirmasLangas); // OK! (use whatever code you want)

        finish();
    }
}
