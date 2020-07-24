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
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is "new records" page to add record
 */

public class F_NewEntryActivity extends Activity {
    EditText Edittype,Edittime,Editcarlories,Edittotal_Fat,Editcarbohydrate;
    Button save,cancel;
    Database_nutrition f_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_new_entry);

        Edittype=(EditText) findViewById(R.id.type_value);
        Edittime= (EditText) findViewById(R.id.time_vlaue);
        Editcarlories=(EditText) findViewById(R.id.calories_value);
        Edittotal_Fat=(EditText) findViewById(R.id.total_fat_value);
        Editcarbohydrate=(EditText) findViewById(R.id.carbohydrate_value);
        SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd hh:mm");
        Edittime.setText(format.format(new Date()));

        save = (Button) findViewById(R.id.f_Save);
        cancel= (Button) findViewById(R.id.f_Cancel);

        f_db =new Database_nutrition(this);
        f_db.setWritable();

        save.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String type= Edittype.getText().toString();
                if (type.matches("")) {
                    Toast.makeText(getApplicationContext(), R.string.f_type, Toast.LENGTH_LONG).show();
                    return;
                }
                String time =Edittime.getText().toString();
                String carlories=Editcarlories.getText().toString();
                if (carlories.matches("")) {
                    Toast.makeText(getApplicationContext(), R.string.f_carlories, Toast.LENGTH_LONG).show();
                    return;
                }
                String total_Fat=Edittotal_Fat.getText().toString();
                if (total_Fat.matches("")) {
                    Toast.makeText(getApplicationContext(), R.string.f_total_Fat, Toast.LENGTH_LONG).show();
                    return;
                }
                String carbohydrate = Editcarbohydrate.getText().toString();
                if (carbohydrate.matches("")) {
                    Toast.makeText(getApplicationContext(), R.string.f_carbohydrate, Toast.LENGTH_LONG).show();
                    return;
                }

                f_db.insert(type, time, carlories,total_Fat, carbohydrate);
                Toast t = Toast.makeText(getApplicationContext(), R.string.f_saveConfirm, Toast.LENGTH_LONG);
                t.show();
                refreshActivity();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(F_NewEntryActivity.this);
                builder.setTitle(R.string.sure_to_cancel);
                builder.setPositiveButton(R.string.f_ok, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        refreshActivity();
                    }
                });
                builder.setNegativeButton(R.string.auto_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public void refreshActivity(){
        finish();
        Intent intent = getIntent();
        startActivity(intent);
    }
}



