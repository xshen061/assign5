package com.example.assign5;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import java.util.Calendar;
/**
 *  MIT License
 *
 *         Copyright (c) [year] [fullname]
 *
 *         Permission is hereby granted, free of charge, to any person obtaining a copy
 *         of this software and associated documentation files (the "Software"), to deal
 *         in the Software without restriction, including without limitation the rights
 *         to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *         copies of the Software, and to permit persons to whom the Software is
 *         furnished to do so, subject to the following conditions:
 *
 *         The above copyright notice and this permission notice shall be included in all
 *         copies or substantial portions of the Software.
 *
 *         THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *         IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *         FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *         AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *         LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *         OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *         SOFTWARE.
 */
/**
 * author: Cynthia Xia Sheng 2020
 */

/**
 * A simple {@link Fragment} subclass for list of automobile records
 */
public class AutoHistFragment extends Fragment {

    private View view;
    private EditText editTextTime;
    private EditText editTextPrice;
    private EditText editTextLiters;
    private EditText editTextKilo;
    private EditText editTextId;
    private Button btDel;
    private Button btUpdate;
    private AutoDatabaseHelper dpHelper;

    public AutoHistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_auto_hist, container, false);
        dpHelper=new AutoDatabaseHelper(getActivity());
        dpHelper.setWritable();
        Bundle bundle = this.getArguments();
        final long id = bundle.getLong("id");
        String year = bundle.getString("year");
        String month = bundle.getString("month");
        String day = bundle.getString("day");

        String price = bundle.getString("price");
        String liters = bundle.getString("liters");
        String kilo = bundle.getString("kilo");
        editTextId=view.findViewById(R.id.fragmentId);
        editTextId.setText(""+id);
        editTextTime=view.findViewById(R.id.fragmentTime);
        editTextTime.setText(year+" - "+month+" - "+day);
        editTextPrice=view.findViewById(R.id.fragmentPrice);
        editTextPrice.setText(price);
        editTextLiters=view.findViewById(R.id.fragmentLiters);
        editTextLiters.setText(liters);
        editTextKilo=view.findViewById(R.id.fragmentKilo);
        editTextKilo.setText(kilo);

        btDel = view.findViewById(R.id.autoFragmentDel);
        btDel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                dpHelper.delete(id);
                getActivity().finish();
                Intent intent = getActivity().getIntent();
                startActivity(intent);

            }
        });

        btUpdate =view.findViewById(R.id.autoFragmentUpdate);

        btUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String strYear = calendar.get(Calendar.YEAR)+"";
                String strMonth = calendar.get(Calendar.MONTH)+1+"";
                String strDay = calendar.get(Calendar.DAY_OF_MONTH)+"";
                String strPrice=editTextPrice.getText().toString();
                String strLiters=editTextLiters.getText().toString();
                String strKilo=editTextKilo.getText().toString();
                dpHelper.update(id,strYear,strMonth,strDay,strPrice,strLiters,strKilo);
                getActivity().finish();
                Intent intent = getActivity().getIntent();
                startActivity(intent);
            }
        });

        return view;
    }

}
