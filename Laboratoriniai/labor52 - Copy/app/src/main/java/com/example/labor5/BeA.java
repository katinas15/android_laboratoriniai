package com.example.labor5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BeA extends Fragment {

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_su_a, container, false);
        Bundle bundle = this.getArguments();
        String string = bundle.getString("text");
        textView = view.findViewById(R.id.textView);

        int balses = 0;
        int didziosios = 0;
        for( int i = 0; i < string.length( ); i++ )
        {
            char temp = string.charAt( i );
            if(Character.isUpperCase(temp)) didziosios++;
            temp = Character.toLowerCase(temp);
            if(temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u' ) balses++;
        }
        textView.setText("zodyje yra " + string.length() + " raides \n"
            + balses + " balses \n"
            + didziosios + " didziosios \n"
            + (string.length() - didziosios) + " mazosios");

        return view;
    }
}