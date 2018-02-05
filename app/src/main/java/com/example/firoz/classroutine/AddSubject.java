package com.example.firoz.classroutine;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;

public class AddSubject extends AppCompatActivity {

    private Spinner spinner;
    private TextView fromTime, toTime;
    private EditText courseTitle, courseCode, roomNumber, courseTeacher;
    public static String days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        fromTime = (TextView) findViewById(R.id.from_time);
        toTime = (TextView) findViewById(R.id.to_time);
        courseTitle = (EditText) findViewById(R.id.course_title);
        courseCode = (EditText) findViewById(R.id.course_code);
        roomNumber = (EditText) findViewById(R.id.room_no);
        courseTeacher = (EditText) findViewById(R.id.teacher_name);

        // Set the Spinner
        spinner = (Spinner) findViewById(R.id.my_spinner_id);

        spinner.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_sample_layout, R.id.my_day_id, getResources().getStringArray(R.array.days)));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                days = String.valueOf(adapterView.getItemAtPosition(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Time set
        int hours = new Time(System.currentTimeMillis()).getHours();
        int minute = new Time(System.currentTimeMillis()).getMinutes();

        String timeFormat = "AM";

        if (hours == 0) {
            hours = 12;
            timeFormat = "AM";
        } else if (hours > 12) {
            hours -= 12;
            timeFormat = "PM";
        }
        fromTime.setText(hours + ":" + minute + " " + timeFormat);
        toTime.setText(hours + ":" + minute + " " + timeFormat);

    }

    public void setTheTime(View view) {

        if (view.getId() == R.id.from_time) {

            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {

                    String timeFormat = "AM";

                    if (h == 0) {
                        h = 12;
                        timeFormat = "AM";
                    } else if (h > 12) {
                        h -= 12;
                        timeFormat = "PM";
                    } else if (h == 12) {
                        h = 12;
                        timeFormat = "PM";
                    }
                    fromTime.setText(h + ":" + m + " " + timeFormat);
                }
            }, new TimePicker(this).getCurrentHour(), new TimePicker(this).getCurrentMinute(),                         false).show();

        } else if (view.getId() == R.id.to_time) {


            new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int h, int m) {

                    String timeFormat = "AM";

                    if (h == 0) {
                        h = 12;
                        timeFormat = "AM";
                    } else if (h > 12) {
                        h -= 12;
                        timeFormat = "PM";
                    } else if (h == 12) {
                        h = 12;
                        timeFormat = "PM";
                    }
                    toTime.setText(h + ":" + m + " " + timeFormat);

                }
            }, new TimePicker(this).getCurrentHour(), new TimePicker(this).getCurrentMinute(), false).show();
        }
    }


    public void saveToDatabase(View view) {

        MyDB myDB = new MyDB(this);


        if (courseTitle.getText().toString().equals("")) {

            Toast.makeText(this, "Pls Input The Course Title", Toast.LENGTH_SHORT).show();
            return;
        }

        long checker = myDB.saveData(days, fromTime.getText().toString(), toTime.getText().toString(), courseTitle.getText().toString().trim(), courseCode.getText().toString(),
                roomNumber.getText().toString(), courseTeacher.getText().toString());

        if (checker > 0)
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Not saved", Toast.LENGTH_SHORT).show();

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
