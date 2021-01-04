package com.example.a4variantask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class GrafikasFragment extends Fragment {

    private static int iterations = 0;

    static float step = 0.1f;
    public static GraphView graph = null;
    View allView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_grafikas, container, false);
        allView = v;
        graph = (GraphView) v.findViewById(R.id.graph);
        registerForContextMenu(graph);
        return v;
    }


    public static void draw(float k, float b, float min, float max){
        //f(x) = loga(x)

        graph.removeAllSeries();

        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
            graph.addSeries(series);
            float x = min;
            for(float i = min; i < max; i+=step){
//                if(x >= max) continue;
                DataPoint point = new DataPoint(i, k*i + b);

                series.appendData(point, false, 20);
            }

//            LineGraphSeries<DataPoint> series = new LineGraphSeries < > (new DataPoint[] {
//                    new DataPoint(0, 1),
//                    new DataPoint(Integer.valueOf(0), Integer.valueOf(1)),
//                    new DataPoint(Integer.valueOf(0), Integer.valueOf(2)),
//                    new DataPoint(Integer.valueOf(1), Integer.valueOf(3)),
//                    new DataPoint(Integer.valueOf(2), Integer.valueOf(4))
//            });

        } catch (IllegalArgumentException e) {
            Log.d("a", "error");
        }
    }

}