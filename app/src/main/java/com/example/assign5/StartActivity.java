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
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * The main page for the Apps, shows the toolbar with 4 items
 */


public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button activityTrackingButton = findViewById(R.id.activity_tracking);
        final Intent activityTrackingIntent = new Intent(this, ActivityTrackingActivity.class);
        activityTrackingButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(activityTrackingIntent);
            }
        });

        Button nutritionButton = findViewById(R.id.nutrition);
        final Intent nutritionIntent = new Intent(this,f_activity.class);
        nutritionButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(nutritionIntent);
            }
        });

        Button houseButton = findViewById(R.id.house);
        final Intent houseIntent = new Intent(this, trackingHouseActivity.class);
        houseButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(houseIntent);
            }
        });

        Button autoButton = findViewById(R.id.automobile);
        final Intent autoIntent = new Intent(this, AutomobileActivity.class);
        autoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(autoIntent);
            }
        });

    }
    }

