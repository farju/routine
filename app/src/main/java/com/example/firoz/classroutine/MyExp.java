package com.example.firoz.classroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Firoz on 10/13/2017.
 */

public class MyExp extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> myList = new ArrayList<>();
    private HashMap<String, ArrayList<String>> listMap = new HashMap<>();

    public MyExp(Context context, ArrayList<String> myList, HashMap<String, ArrayList<String>> listMap) {
        this.context = context;
        this.myList = myList;
        this.listMap = listMap;
    }

    @Override
    public int getGroupCount() {
        return myList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listMap.get(myList.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return myList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listMap.get(myList.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        String title = (String) getGroup(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            try {

                view = inflater.inflate(R.layout.parent_list, viewGroup, false);
            } catch (Exception e) {
            }
        }

        TextView textView = view.findViewById(R.id.parent_title_id);
        TextView textView2 = view.findViewById(R.id.parent_second_title);
        textView.setText(title);
        textView2.setText(ExpListActivity.times.get(i));
        ImageView imageView = view.findViewById(R.id.parent_icone);


        if (b) {
            imageView.setImageResource(R.drawable.arrow_down_grey);
        } else {
            imageView.setImageResource(R.drawable.arrow_right_grey);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        String topics = (String) getChild(i, i1);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.child_list, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.child_txt_id);
        textView.setText(topics);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
