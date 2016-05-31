package com.example.android.meand100_v2.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.meand100_v2.R;

import java.util.ArrayList;

/**
 * Created by Idan on 27/01/2016.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<HeaderStory> {

    private final Context context;
        private final ArrayList<HeaderStory> values;

        public MySimpleArrayAdapter(Context context, ArrayList<HeaderStory> values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.story_item, parent, false);
            ImageView photoImageView = (ImageView) rowView.findViewById(R.id.story_photo);
            TextView headerTextView = (TextView) rowView.findViewById(R.id.header_text);
            TextView distanceTextView = (TextView) rowView.findViewById(R.id.distance_text);
            TextView dateTextView = (TextView) rowView.findViewById(R.id.date_text);
            TextView timeTextView = (TextView) rowView.findViewById(R.id.time_text);
            //photoImageView.setImageResource(R.drawable.abc_btn_switch_to_on_mtrl_00012);
            headerTextView.setText(values.get(position).getHeader());
            distanceTextView.setText(values.get(position).getLocation().toString());
            dateTextView.setText(values.get(position).getDate());
            timeTextView.setText(values.get(position).getTime());

            return rowView;
        }
    }

