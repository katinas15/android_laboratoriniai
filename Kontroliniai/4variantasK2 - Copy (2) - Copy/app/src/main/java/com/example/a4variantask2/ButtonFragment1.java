package com.example.a4variantask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonFragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_button1, container, false);

        Button button1 = (Button) v.findViewById(R.id.youtube);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WebViewFragment.setWeb("http://youtube.com");
            }
        });


        Button button2 = (Button) v.findViewById(R.id.google);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WebViewFragment.setWeb("http://google.com");
            }
        });

        return v;
    }


}