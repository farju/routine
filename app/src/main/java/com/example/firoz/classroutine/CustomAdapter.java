package com.example.firoz.classroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Firoz on 10/13/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;

    ArrayList<String> allTitle = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();

    public CustomAdapter(Context context, ArrayList<String> titles, ArrayList<String> times) {
        this.context = context;

        allTitle = titles;
        this.times = times;
    }

    @Override
    public int getCount() {
       return allTitle.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.my_title_list, viewGroup, false);
        }

        TextView title = view.findViewById(R.id.subject_title_id);
        TextView time = view.findViewById(R.id.subject_time_id);

        title.setText(allTitle.get(i).toString());
        time.setText(times.get(i).toString());

        return view;
    }
}
