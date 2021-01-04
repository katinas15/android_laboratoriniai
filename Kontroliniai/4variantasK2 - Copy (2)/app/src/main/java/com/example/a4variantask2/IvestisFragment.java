package com.example.a4variantask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class IvestisFragment extends Fragment {


    Button button;
    EditText k;
    EditText b;
    EditText min;
    EditText max;
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


}