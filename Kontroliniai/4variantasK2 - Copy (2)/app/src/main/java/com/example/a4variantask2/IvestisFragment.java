package com.example.a4variantask2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class IvestisFragment extends Fragment {


    Button button;
    EditText k;
    EditText b;
    EditText min;
    EditText max;
    TextView text;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ivestis, container, false);
        Button button1 = (Button) v.findViewById(R.id.skaiciuoti);
        k = v.findViewById(R.id.k);
        b = v.findViewById(R.id.b);
        min = v.findViewById(R.id.min);
        max = v.findViewById(R.id.max);
        text = v.findViewById(R.id.context2);
        registerForContextMenu(text);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GrafikasFragment.draw(Float.parseFloat(String.valueOf(k.getText())), Float.parseFloat(String.valueOf(b.getText())), Float.parseFloat(String.valueOf(min.getText())), Float.parseFloat(String.valueOf(max.getText())));
            }
        });

        return v;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getActivity().getMenuInflater().inflate(R.menu.click_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.apie:
                Intent langas = new Intent(getActivity(), ApieActivity.class);

                startActivity(langas);
                return true;
            case R.id.kaip:
                Intent langas1 = new Intent(getActivity(), KaipActivity.class);

                startActivity(langas1);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }


}