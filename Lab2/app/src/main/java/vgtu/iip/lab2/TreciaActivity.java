package vgtu.iip.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TreciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trecia);
        TextView laukasIsvedimui = (TextView) findViewById(R.id.rezultatas);
//        Log.i("TODO", "Gaukite perduotus duomenis");
        Log.i("Veiksmas", "Skaiciuojamas zodziu skaicius");

        Intent dabar = this.getIntent();

        String tekstas = (String) dabar.getSerializableExtra("ivestis");
        //TODO reiketu patikrinti ar veiksmas buvo ACTION_SEND ar ne, ir pasiimti siunciamus duomenis, kaip kintamaji tekstas
        int kiekis = tekstas.split(" ").length;
        String isvestis = getString(R.string.ivestasTestas)+" '"+tekstas+"' "+getString(R.string.yra)
                +" "+kiekis+" "+getString(R.string.zodziu);
        laukasIsvedimui.setText(isvestis);
    }
}
