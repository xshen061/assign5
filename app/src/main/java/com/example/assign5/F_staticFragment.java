package com.example.assign5;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class F_staticFragment extends android.app.Fragment {

    View view;
    TextView average_calories;
    TextView total_calories;
    TextView avg_label;
    TextView total_label;
    Database_nutrition db;
    Button button;
   // Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_f_static, container, false);

        db = new  Database_nutrition(getActivity());
        average_calories = view.findViewById(R.id.f_average_calories_value);
        total_calories = view.findViewById(R.id.f_total_calories_value);
        avg_label = view.findViewById(R.id.f_average_calories);
        total_label = view.findViewById(R.id.f_total_calories);

        String avg = String.valueOf(db.getAvg());
        String total = String.valueOf( db.getTotal());

        average_calories.setText(avg);
        total_calories.setText(total);
        return view;
    }
  }
