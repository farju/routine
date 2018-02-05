package com.example.firoz.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpListActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ArrayList<String> title;
    private HashMap<String, ArrayList<String>> mapList;

    public static ArrayList<String> times;
    private ArrayList<String> roomNumber;
    private ArrayList<String> teacher;
    private ArrayList<String> code;
    private int lastExpandedPos = -1;
    ExpandableListAdapter listAdapter;

    private MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_list);

        // get the expendable list view
        expandableListView = (ExpandableListView) findViewById(R.id.my_exp_list_view);

        // set data
        setData();

        listAdapter = new MyExp(this, title, mapList);
        expandableListView.setAdapter(listAdapter);


        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPos != -1 && lastExpandedPos != groupPosition) {
                    expandableListView.collapseGroup(lastExpandedPos);
                }
                lastExpandedPos = groupPosition;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {

            }
        });

    }


    private void setData() {

        mapList = new HashMap<>();

        title = new ArrayList<>();
        times = new ArrayList<>();
        roomNumber = new ArrayList<>();
        teacher = new ArrayList<>();
        code = new ArrayList<>();


        title = getIntent().getStringArrayListExtra("title");
        times = getIntent().getStringArrayListExtra("time");
        code = getIntent().getStringArrayListExtra("code");
        roomNumber = getIntent().getStringArrayListExtra("room");
        teacher = getIntent().getStringArrayListExtra("teacher");

        setMapData();

        myDB = new MyDB(this);

    }

    private void setMapData() {


        for (int i = 0; i < title.size(); i++) {

            ArrayList<String> value = new ArrayList<>();

            value.add("Course Code : " + code.get(i));
            value.add("Room : " + roomNumber.get(i));
            value.add("Teacher : " + teacher.get(i));

            mapList.put(title.get(i), value);

        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
