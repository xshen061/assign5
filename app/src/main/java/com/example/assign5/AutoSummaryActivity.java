package com.example.assign5;
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
import android.content.Intent;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;



public class AutoSummaryActivity extends Activity {
    protected static final String ACTIVITY_NAME = "AutoSummaryActivity";
    TextView year;
    TextView jan,janSum,feb,febSum,mar,marSum,apr,aprSum,may,maySum,jun,junSum,jul,julSum,aug,augSum,
            sep,sepSum,oct,octSum,nov,novSum,dec,decSum;
    EditText yearSelect;
    Button yearSubmit;

    ArrayList<String> list=new ArrayList<>();
    AutoDatabaseHelper aHelper;


    Calendar calendar = Calendar.getInstance();
    int thisYear = calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_summary);

        year=(TextView) findViewById(R.id.autoSum_year);
        jan=(TextView) findViewById(R.id.autoSum_Jan);
        janSum=(TextView) findViewById(R.id.autoSum_JanValue);
        feb=(TextView) findViewById(R.id.autoSum_Feb);
        febSum=(TextView) findViewById(R.id.autoSum_FebValue);
        mar=(TextView) findViewById(R.id.autoSum_Mar);
        marSum=(TextView) findViewById(R.id.autoSum_MarValue);
        apr=(TextView) findViewById(R.id.autoSum_Apr);
        aprSum=(TextView) findViewById(R.id.autoSum_AprValue);
        may=(TextView) findViewById(R.id.autoSum_May);
        maySum=(TextView) findViewById(R.id.autoSum_MayValue);
        jun=(TextView) findViewById(R.id.autoSum_Jun);
        junSum=(TextView) findViewById(R.id.autoSum_JunValue);
        jul=(TextView) findViewById(R.id.autoSum_Jul);
        julSum=(TextView) findViewById(R.id.autoSum_JulValue);
        aug=(TextView) findViewById(R.id.autoSum_Aug);
        augSum=(TextView) findViewById(R.id.autoSum_AugValue);
        sep=(TextView) findViewById(R.id.autoSum_Sep);
        sepSum=(TextView) findViewById(R.id.autoSum_SepValue);
        oct=(TextView) findViewById(R.id.autoSum_Oct);
        octSum=(TextView) findViewById(R.id.autoSum_OctValue);
        nov=(TextView) findViewById(R.id.autoSum_Nov);
        novSum=(TextView) findViewById(R.id.autoSum_NovValue);
        dec=(TextView) findViewById(R.id.autoSum_Dec);
        decSum=(TextView) findViewById(R.id.autoSum_DecValue);

        yearSubmit = (Button) findViewById(R.id.autoSum_submit);
        yearSelect = (EditText) findViewById(R.id.autoSum_yearSelect);

        aHelper = new AutoDatabaseHelper(this);
        aHelper.setWritable();

        /*SumQuery query=new SumQuery();
        query.execute();*/
        display(thisYear);

        yearSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisYear = Integer.parseInt(yearSelect.getText().toString());
                display(thisYear);
                /*SumQuery query2=new SumQuery();
                 query2.execute();*/
                yearSelect.setText("");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        aHelper.closeDatabase();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    public void refreshActivity(){
        finish();
        Intent intentRef = getIntent();
        startActivity(intentRef);
    }


    public void display(int thisYear){

        year.setText(thisYear+"");

        janSum.setText(aHelper.getSum(thisYear).get(0));
        febSum.setText(aHelper.getSum(thisYear).get(1));
        marSum.setText(aHelper.getSum(thisYear).get(2));
        aprSum.setText(aHelper.getSum(thisYear).get(3));
        maySum.setText(aHelper.getSum(thisYear).get(4));
        junSum.setText(aHelper.getSum(thisYear).get(5));
        julSum.setText(aHelper.getSum(thisYear).get(6));
        augSum.setText(aHelper.getSum(thisYear).get(7));
        sepSum.setText(aHelper.getSum(thisYear).get(8));
        octSum.setText(aHelper.getSum(thisYear).get(9));
        novSum.setText(aHelper.getSum(thisYear).get(10));
        decSum.setText(aHelper.getSum(thisYear).get(11));
    }
 }
