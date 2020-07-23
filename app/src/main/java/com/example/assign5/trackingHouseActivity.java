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
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.assign5.House_DatabaseHelper.TABLE_NAME;


public class trackingHouseActivity extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "TrackingHouseActivity";
    private SQLiteDatabase tempDB;
    private ArrayList<Map> userList = new ArrayList();
    private Cursor cursor;
    private ProgressBar progressBar;
    private ChatAdapter therAdapter;
    private House_DatabaseHelper dbHelper;
    private int requestCode = 1;

    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {

            case R.id.helpthermo:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(R.string.help);
                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.activity_house_help, null);
                builder2.setView(dialogView);
                builder2.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
              AlertDialog dialog2 = builder2.create();
                dialog2.show();
                break;
        }return true;
    }
        @Override
        public boolean onCreateOptionsMenu (Menu m){
            getMenuInflater().inflate(R.menu.toolbar_thermo, m );
            return true;
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        TrackingAsync init = new TrackingAsync();
        init.execute();

        ListView listview = findViewById(R.id.thermView);
        therAdapter=new ChatAdapter(trackingHouseActivity.this);
        listview.setAdapter(therAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("id",therAdapter.getItemId(position)+"");
                bundle.putString("description",therAdapter.getItem(position)+"");

                FragmentThermo messageFragment = new FragmentThermo();
                messageFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.h_listview_Frame,messageFragment).addToBackStack(null).commit();
            }
        });
    }
    private class ChatAdapter extends ArrayAdapter<Map<String, Object>> {
        private ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return userList.size();
        }

        public Map<String, Object> getItem(int position) {
            return userList.get(position);
        }

        public long getItemId(int position){
            Map<String, Object> content = getItem(position);
            return Long.parseLong(content.get("id").toString());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View result = inflater.inflate(R.layout.activity_house_track, parent, false);
            if (!userList.isEmpty()) {
                Map<String, Object> content = getItem(position);
                TextView message1 = (TextView) result.findViewById(R.id.record);
                message1.setText(content.get("description").toString());
            }
            return result;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.requestCode == requestCode && data != null) {
            Long id = data.getLongExtra("id", -1);
            tempDB.delete(TABLE_NAME, House_DatabaseHelper.ID + "=" + id, null);
            refreshActivity();
        }
    }

    class TrackingAsync extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            SystemClock.sleep(100);
            progressBar.setProgress(10);
            dbHelper = new House_DatabaseHelper(trackingHouseActivity.this);
            tempDB = dbHelper.getWritableDatabase();
            SystemClock.sleep(200);
            progressBar.setProgress(30);

        //populate activity list
        cursor = tempDB.rawQuery("select * from " + TABLE_NAME,null );
        cursor.moveToFirst();
        while(!cursor.isAfterLast() ) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", cursor.getString(cursor.getColumnIndex(House_DatabaseHelper.ID)));
            String day = cursor.getString(cursor.getColumnIndex(House_DatabaseHelper.DAY));
            String hour = cursor.getString(cursor.getColumnIndex(House_DatabaseHelper.HOUR));
            String minutes = cursor.getString(cursor.getColumnIndex(House_DatabaseHelper.MINUTE));
            String temperature = cursor.getString(cursor.getColumnIndex(House_DatabaseHelper.Temperature));
            row.put("Day of  Week", day);
            row.put("description", day + " , "+ hour +": "  + minutes + " , "+ "Temp -> "+ temperature);
            userList.add(row);
            cursor.moveToNext();
        }
            SystemClock.sleep(500);
            progressBar.setProgress(80);


            findViewById(R.id.addButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        //Intent intent = new Intent(trackingHouseActivity.this, AddHouseActivity.class);

                    Fragment_AddThermo messageFragment = new Fragment_AddThermo();
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.h_listview_Frame,messageFragment).addToBackStack(null).commit();
                    }
//                }
            });
            SystemClock.sleep(200);
            progressBar.setProgress(100);
            return null;
        }

        protected void onProgressUpdate(Integer ...values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
           therAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE );
        }
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
            Log.i(ACTIVITY_NAME, "In onDestroy()");
        }

        public void refreshActivity(){
            finish();
            Intent intent = getIntent();
            startActivity(intent);
        }
    }


