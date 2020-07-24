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
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

/**
 * This is the detail page of food, user can update, delete
 */

public class F_DetailActivity extends Activity {

    View view;
    Button cancel, update, delete;
    EditText Edit_type, Edit_Time,Edit_Calories, Edit_Total_Fat, Edit_Total_Carbohydrate;
    SQLiteDatabase writeableDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_detail);

        Edit_type = findViewById(R.id.type_value);
        Edit_Time = findViewById(R.id.time_value);
        Edit_Calories =findViewById(R.id.calories_value);
        Edit_Total_Fat = findViewById(R.id.total_fat_value);
        Edit_Total_Carbohydrate = findViewById(R.id.carbohydrate_value);
        delete = findViewById(R.id.f_Delete);
        update=findViewById(R.id.f_Update);
        cancel= findViewById(R.id.f_Cancel);

        final Database_nutrition dbHelper = new Database_nutrition(this);
        writeableDB = dbHelper.getWritableDatabase();

        Bundle bundle = getIntent().getBundleExtra("bundle");
        final long rowId = bundle.getLong(F_historyActivity.ID);
        String type = bundle.getString("type");
        String time = bundle.getString("time");
        String calories = bundle.getString("calories");
        String total_Fat = bundle.getString("total_Fat");
        String carbohydrate = bundle.getString("carbohydrate");

        Edit_type.setText(type);
        Edit_Time.setText(time);
        Edit_Calories.setText(calories);
        Edit_Total_Fat.setText(total_Fat);
        Edit_Total_Carbohydrate.setText(carbohydrate);

        final Intent startIntent = new Intent(this,F_historyActivity.class);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder builder1 = new android.app.AlertDialog.Builder(F_DetailActivity.this);
                builder1.setTitle(getResources().getString(R.string.t_wanna_update));
                // Add the buttons
                builder1.setPositiveButton(R.string.t_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //update value
                        String type = Edit_type.getText().toString();
                        String time =  Edit_Time .getText().toString();
                        String calories  =  Edit_Calories .getText().toString();
                        String total_Fat = Edit_Total_Fat.getText().toString();
                        String carbohydrate = Edit_Total_Carbohydrate .getText().toString();

                        dbHelper.update(rowId ,type,time,calories,total_Fat,carbohydrate);
                        Toast toast = Toast.makeText(F_DetailActivity.this,
                                getResources().getString(R.string.f_update_success), Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        startActivity(startIntent);
                    }
                });
                builder1.setNegativeButton(R.string.t_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                // Create the AlertDialog
                android.app.AlertDialog dialog1 = builder1.create();
                dialog1.show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(F_DetailActivity.this);
                builder.setTitle(R.string.sure_to_delete);
                builder.setPositiveButton(R.string.t_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dbHelper.delete(rowId);
                        Toast toast = Toast.makeText(F_DetailActivity.this,
                                getResources().getString(R.string.f_delete_success), Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                        startActivity(startIntent);

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(F_DetailActivity.this);
                builder.setTitle(R.string.sure_to_cancel);
                // Add the buttons
                builder.setPositiveButton(R.string.f_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      finish();
                    }
                });
                builder.setNegativeButton(R.string.f_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

   }
}
