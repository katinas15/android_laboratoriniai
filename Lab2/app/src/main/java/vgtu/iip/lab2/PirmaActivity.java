package vgtu.iip.lab2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PirmaActivity extends AppCompatActivity {

    final static int IVEDIMAS = 1;
    TextView isvedimoLaukas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirma);
        isvedimoLaukas = (TextView) findViewById(R.id.tekstas);
    }

    public void atvertiVeiklaRezultatuGavimui(View w){
//        Log.i("TODO", "reikia realizuoti startActivityForresults");
        //TODO sukurti intent ir ji paleisti, laukiant rezultatu
        Log.i("Veiksmas", "Paspaudziama ant pirmojo mygtuko");
        Intent AntrasLangas = new Intent(PirmaActivity.this, AntraActivity.class);
        startActivityForResult(AntrasLangas, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i("TODO", "Grizo is AntraActivity");
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Log.i("check", "OK");
                String result=data.getStringExtra("ivestis");
                Log.i("INTENT", String.valueOf(requestCode)); //patikriname nuo kokio intent
                Log.i("Veiksmas", data.toString());
                Log.i("Veiksmas", "Grazinami duomenys; Kodas -  " + resultCode + " ;Duomenys - " + result);
                isvedimoLaukas.setText(result);
            } else if(resultCode == RESULT_CANCELED){
                Log.i("check", "NOT OK");
            }
        }



        //TODO patikrinti nuo kokios veiklos ir kokius rezultatus gavo, tada pasiimti siunciama reiksme ir ja paduoti vietoj sekancioje eiluteje irasyto teksto


    }

    public void sukurtiExplicitIntent(View w){
        String tekstas = isvedimoLaukas.getText().toString();
//        Log.i("TODO", "reikia sukurti explicit intent su perduodamais duomenimis");
        Log.i("Veiksmas", "Skaiciuojami zodziai i kuriamas explicit intent");
        //TODO sukurti explicit intent, su putExtra perduoti tekstas kintamaji ir tada paleisti, nelaukiant rezultatu
        Intent TreciasLangas = new Intent(PirmaActivity.this, TreciaActivity.class);
        TreciasLangas.setAction(Intent.ACTION_SEND);
        TreciasLangas.putExtra("ivestis", tekstas);

        startActivity(TreciasLangas);
    }
    public void sukurtiImplicitIntent(View w){
        String duomenysSiuntimui = isvedimoLaukas.getText().toString();
//        Log.i("TODO", "reikia sukurti implicit intent");
        Log.i("Veiksmas", "Sukuriamas explicit intent ir tekstas siunciamas i kita APP");
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, duomenysSiuntimui);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        //TODO sukurti explicit intent, jame perduoti kintamaji duomenysSiuntimui (tekstiniai duomenys)
        //TODO tada kitas intent bus tam, kad vartotojas galetu rinktis norima programa ir ja paleisti nelaukaint rezultatu
    }
}
