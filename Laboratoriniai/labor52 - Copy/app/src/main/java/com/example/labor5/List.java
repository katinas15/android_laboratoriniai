package com.example.labor5;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class List extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("aaaa");
        arrayList.add("dfygtdf");
        arrayList.add("lAbs");
        arrayList.add("Viso Gero");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), arrayList.get(i), Toast.LENGTH_SHORT).show();
                fragmentas(arrayList.get(i));
            }
        });
        return view;
    }

    private void fragmentas(String string){
        if(string.indexOf('a') >= 0 || string.indexOf('A') >= 0){
            MainActivity.suA(string);
        } else {
            MainActivity.beA(string);
        }
    }
}