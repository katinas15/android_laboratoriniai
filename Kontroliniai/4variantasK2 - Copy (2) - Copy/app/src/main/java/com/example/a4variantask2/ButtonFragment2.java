package com.example.a4variantask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ButtonFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_button2, container, false);

        Button button1 = (Button) v.findViewById(R.id.delfi);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WebViewFragment.setWeb("http://delfi.com");
            }
        });


        Button button2 = (Button) v.findViewById(R.id.ebay);
        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                WebViewFragment.setWeb("http://ebay.com");
            }
        });

        return v;
    }


}