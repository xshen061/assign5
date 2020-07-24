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
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass for list of exercise records
 */
public class ActivityTrackingListViewFragment extends Fragment {
    private ArrayList<Map> activityList = new ArrayList();

    public ActivityTrackingListViewFragment() {
        // Required empty public constructor
    }

    public void init(ArrayList<Map> activityList) {
        this.activityList = activityList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_activity_tracking_list_view, container, false);
        ListView listView = fragView.findViewById(R.id.t_activitListView);
        final ChatAdapter chatAdapter = new ChatAdapter(getActivity());
        listView.setAdapter(chatAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map message = chatAdapter.getItem(position);
                long idInDb =  chatAdapter.getItemId(position);
                Bundle bundle = new Bundle();
                bundle.putLong(ActivityTrackingActivity.ID,idInDb);
                bundle.putString(ActivityTrackingDatabaseHelper.TYPE, message.get(ActivityTrackingDatabaseHelper.TYPE).toString());
                bundle.putString(ActivityTrackingDatabaseHelper.TIME, message.get(ActivityTrackingDatabaseHelper.TIME).toString());
                bundle.putString(ActivityTrackingDatabaseHelper.DURATION, message.get(ActivityTrackingDatabaseHelper.DURATION).toString());
                bundle.putString(ActivityTrackingDatabaseHelper.COMMENT, message.get(ActivityTrackingDatabaseHelper.COMMENT).toString());

                Intent intent = new Intent(getActivity(), ActivityTrackingEditActivity.class);
                intent.putExtra("bundle", bundle);
                getActivity().finish();
                startActivity(intent);
            }
        });
        return fragView;
    }
    class ChatAdapter extends ArrayAdapter<Map<String, Object>> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount(){

            return activityList.size();
        }
        public Map<String, Object> getItem(int position){

            return activityList.get(position);
        }

        private int getImageId(String type) {
            switch (type) {
                case "Running":
                case "撒鸭子":  return R.drawable.running;
                case "Walking":
                case "走道": return R.drawable.hiking;
                case "Biking":
                case "骑车子": return R.drawable.biking;
                case "Swimming":
                case "游泳": return R.drawable.swimming;
                case "Skating":
                case "滑出溜": return R.drawable.skating;
            }
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = getActivity().getLayoutInflater();
            View result = inflater.inflate(R.layout.t_tracking_row, null);
            if (!activityList.isEmpty()) {
                Map<String, Object> content = getItem(position);

                ImageView img = result.findViewById(R.id.t_activity_row_icon);
                img.setImageResource(getImageId(content.get("type").toString()));
                TextView message = (TextView) result.findViewById(R.id.t_activity_row_description);
                message.setText(content.get("description").toString()); // get the string at position
            }
            return result;
        }
        public long getItemId(int position){
            Map<String, Object> content = getItem(position);
            return Long.parseLong(content.get("id").toString());
        }
    }
}
