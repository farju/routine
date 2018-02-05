package com.example.firoz.classroutine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAllSubjects extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    public static String days;
    int currentItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_subjects);

        // get value
        titles = getIntent().getStringArrayListExtra("title");
        times = getIntent().getStringArrayListExtra("time");
        id = getIntent().getStringArrayListExtra("id");

        // set spinner
        spinner = (Spinner) findViewById(R.id.my_spinner_id_delete);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_sample_layout, R.id.my_day_id, getResources().getStringArray(R.array.days)));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                days = String.valueOf(adapterView.getItemAtPosition(i));

                if (currentItem == i) {
                    return;
                } else {
                    getList(days);
                    currentItem = i;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // set list view
        listView = (ListView) findViewById(R.id.my_list_view);
        CustomAdapter customAdapter = new CustomAdapter(this, titles, times);
        listView.setAdapter(customAdapter);

        // add onclick listener to listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                /*
                  For showing Alert Diolog
                   */

                final int x = i;

                AlertDialog.Builder builder = new AlertDialog.Builder(ListAllSubjects.this);
                builder.setTitle("Delete...?");
                builder.setMessage("Are You sure ?");
                builder.setIcon(R.drawable.ic_warning_black_24dp);
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i1) {

                        deleteItem(x);

                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(ListAllSubjects.this, "Not deleted", Toast.LENGTH_SHORT).show();

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

                // End the alert dialog


            }
        });

    }

    private void deleteItem(int i) {


        MyDB myDB = new MyDB(ListAllSubjects.this);

        int ck = myDB.deleteSub(days, id.get(i));

        if (ck > 0) {

            Toast.makeText(ListAllSubjects.this, "Item deleted", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(ListAllSubjects.this, " " + days + " " + titles.get(i), Toast.LENGTH_SHORT).show();
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


    public void getList(String day) {

        MyDB myDB = new MyDB(this);
        Cursor cursor = myDB.allSubjects(day);

        titles = new ArrayList<>();
        times = new ArrayList<>();
        id = new ArrayList<>();

        if (cursor.getCount() > 0) {

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                titles.add(cursor.getString(3));
                String s = cursor.getString(1) + "  To  " + cursor.getString(2);
                times.add(s);
                id.add(cursor.getString(0));
                //  courseCode.add(cursor.getString(4));
                //   roomNumber.add(cursor.getString(5));
                //  teacher.add(cursor.getString(6));

            }

            //  Intent intent = new Intent(this, ListAllSubjects.class);
            //  intent.putStringArrayListExtra("title", titles);
            // intent.putStringArrayListExtra("time", times);
            // intent.putStringArrayListExtra("code", courseCode);
            // intent.putStringArrayListExtra("room", roomNumber);
            //  intent.putStringArrayListExtra("teacher", teacher);
            //   startActivity(intent);

            //   this.finish();


        }

        CustomAdapter customAdapter = new CustomAdapter(this, titles, times);
        listView.setAdapter(customAdapter);

    }
}
