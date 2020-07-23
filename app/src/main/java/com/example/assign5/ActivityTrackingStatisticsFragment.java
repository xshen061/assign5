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
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityTrackingStatisticsFragment extends Fragment {

    private ArrayList<Map> activityList = new ArrayList();

    public ActivityTrackingStatisticsFragment() {
        // Required empty public constructor
    }

    public void init(ArrayList<Map> activityList) {
        this.activityList = activityList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activity_tracking_statistics, container, false);

        Map<String, Integer> minutePerActivityMap = new TreeMap<>();
        Map<String, Integer> activityCountMap = new TreeMap<>();
        DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
        String[] months = symbols.getMonths();;
        for (Map row : activityList) {
            String type = row.get(ActivityTrackingDatabaseHelper.TYPE).toString();
            String timeString = row.get(ActivityTrackingDatabaseHelper.TIME).toString();
            Date time = null;
            try {
                time = ActivityTrackingUtil.getDateFormatter().parse(timeString);
            } catch (Exception e) {
                Log.e(ActivityTrackingStatisticsFragment.class.getName(), "Error parsing time: " + timeString);
                time = new Date();
            }
            Date today =  new Date();

            String durationString = row.get(ActivityTrackingDatabaseHelper.DURATION).toString();
            int duration = Integer.parseInt(durationString);
            String month = months[time.getMonth()];
            Integer minutePerActivity = minutePerActivityMap.get(month);

            if (! (time.getYear() == today.getYear())) {
                continue;
            }
            if (minutePerActivity == null) {
                minutePerActivityMap.put(month, duration);
            } else {
                minutePerActivityMap.put(month, duration + minutePerActivity);
            }

            if (! (time.getMonth() == today.getMonth())) {
                continue;
            }


            Integer activityCount = activityCountMap.get(type);
            if (activityCount == null) {
                activityCountMap.put(type, 1);
            } else {
                activityCountMap.put(type, ++activityCount);
            }
        }

        TextView minutePerActivityView  = view.findViewById(R.id.t_minitePerActivity);
        String mpa = getString(minutePerActivityMap);
        minutePerActivityView.setText(mpa);

        TextView activityCountView  = view.findViewById(R.id.t_activityCount);
        activityCountView.setText(getString(activityCountMap));

        final Intent startIntent = new Intent(getActivity(), ActivityTrackingActivity.class);
        ((Button)view.findViewById(R.id.t_stats_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(startIntent);
            }
        });
         return view;
    }

    @NonNull
    private String getString(Map<String, Integer> minutePerActivityMap) {
        String minutePerActivity = "";
        for (Map.Entry entry : minutePerActivityMap.entrySet()) {
            minutePerActivity += entry.getKey() + " : " + entry.getValue() +"\n";
        }
        return minutePerActivity;
    }

}
