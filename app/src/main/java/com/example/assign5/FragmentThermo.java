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
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static com.example.assign5.House_DatabaseHelper.DAY;
import static com.example.assign5.House_DatabaseHelper.HOUR;
import static com.example.assign5.House_DatabaseHelper.ID;
import static com.example.assign5.House_DatabaseHelper.MINUTE;
import static com.example.assign5.House_DatabaseHelper.TABLE_NAME;
import static com.example.assign5.House_DatabaseHelper.Temperature;


public class FragmentThermo extends Fragment {

    private House_DatabaseHelper dbHelper;
    private SQLiteDatabase tempDB;
    private Button del;
    private Button saveNew;
    private Button save;
    private View view;
    private Cursor cursor;
    private TextView textViewId;
    private Spinner textDay;
    private EditText textHour;
    private EditText textMinute;
    private EditText textTemp;
    String day;
    String hour;
    String minute;
    String temp;
    Bundle bundle;
    String id;


    public FragmentThermo() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_house_edit, container, false);

        bundle = this.getArguments();
       id = bundle.getString("id");

        dbHelper = new House_DatabaseHelper(getActivity());
        tempDB = dbHelper.getWritableDatabase();
        cursor = tempDB.rawQuery("select * from " + TABLE_NAME +" WHERE "+ID+" = " +id,null);
        cursor.moveToFirst();

        textViewId=view.findViewById(R.id.fragmentId);
        textViewId.setText("ID="+id);

        day=cursor.getString(cursor.getColumnIndex(DAY));
        hour=cursor.getString(cursor.getColumnIndex(HOUR));
        minute=cursor.getString(cursor.getColumnIndex(MINUTE));
        temp=cursor.getString(cursor.getColumnIndex(Temperature));

        textDay=view.findViewById(R.id.fragmentWeek);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.weekday, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        textDay.setAdapter(adapter);
        int spinnerPosition = adapter.getPosition(day);
        textDay.setSelection(spinnerPosition);

        textHour=view.findViewById(R.id.fragmentHour);
        textHour.setText(hour,TextView.BufferType.EDITABLE);
        textHour.setFilters(new InputFilter[]{ new InputFilterMinMax("00", "23")});

        textMinute=view.findViewById(R.id.fragmentMinute);
        textMinute.setText(minute,TextView.BufferType.EDITABLE);
        textMinute.setFilters(new InputFilter[]{ new InputFilterMinMax("00", "59")});

        textTemp=view.findViewById(R.id.fragmentTemp);
        textTemp.setText(temp,TextView.BufferType.EDITABLE);
        textTemp.setFilters(new InputFilter[]{ new InputFilterMinMax("1", "50")});

        cursor.close();

        del = view.findViewById(R.id.button_delete_fg_h);
        del.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View var1) {
                    tempDB.delete(TABLE_NAME, ID + "=" + id, null);
                    getActivity().finish();
                    getActivity().getFragmentManager().beginTransaction().remove(FragmentThermo.this).commit();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
            }
        });

        save=view.findViewById(R.id.button_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp = textTemp.getText().toString();
                day = textDay.getSelectedItem().toString();
                hour= textHour.getText().toString();
                minute = textMinute.getText().toString();
                ContentValues input = new ContentValues();
                input.put(DAY, day);
                input.put(HOUR, hour);
                input.put(MINUTE,minute);
                input.put(Temperature, temp);
                tempDB.update(TABLE_NAME,input, "_id="+id , null );

                    getActivity().finish();
                    getActivity().getFragmentManager().beginTransaction().remove(FragmentThermo.this).commit();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);

            }

        });

        saveNew = view.findViewById(R.id.button_saveNewRule_fg_h);
        saveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View var2) {
                temp = textTemp.getText().toString();
                day = textDay.getSelectedItem().toString();
                hour= textHour.getText().toString();
                minute = textMinute.getText().toString();

                ContentValues input = new ContentValues();
                input.put(DAY, day);
                input.put(HOUR, hour);
                input.put(MINUTE,minute);
                input.put(Temperature, temp);
                tempDB.insert(TABLE_NAME, ID, input );

                    getActivity().finish();
                    getActivity().getFragmentManager().beginTransaction().remove(FragmentThermo.this).commit();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);

            }

        });
        return view;
    }
    }
