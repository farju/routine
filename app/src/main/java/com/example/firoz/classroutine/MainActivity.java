package com.example.firoz.classroutine;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    MyDB myDB;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();
    ArrayList<String> courseCode = new ArrayList<>();
    ArrayList<String> roomNumber = new ArrayList<>();
    ArrayList<String> teacher = new ArrayList<>();
    ArrayList<String> id = new ArrayList<>();

    Typeface custom_font;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        myDB = new MyDB(this);

        custom_font = Typeface.createFromAsset(getAssets(), "fonts/newtxt.otf");

        setCustomFont();

    }


    // for set custom font
    private void setCustomFont() {

        Button button = findViewById(R.id.sat);
        Button button1 = findViewById(R.id.sun);
        Button button2 = findViewById(R.id.mon);
        Button button3 = findViewById(R.id.tue);
        Button button4 = findViewById(R.id.wed);
        Button button5 = findViewById(R.id.thu);
        Button button6 = findViewById(R.id.fri);

        button.setTypeface(custom_font);
        button1.setTypeface(custom_font);
        button2.setTypeface(custom_font);
        button3.setTypeface(custom_font);
        button4.setTypeface(custom_font);
        button5.setTypeface(custom_font);
        button6.setTypeface(custom_font);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {

            Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();

        }

        if (id == R.id.action_rate) {

            Toast.makeText(this, "Rate", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.action_share) {

            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_subject_id) {

            // go new activity for add a new subject
            startActivity(new Intent(this, AddSubject.class));

        } else if (id == R.id.edit_subject_id) {
            // for edit

            Toast.makeText(this, "This is not available write now", Toast.LENGTH_LONG).show();

        } else if (id == R.id.delete_subject_id) {
            // for delete
            getList("Saturday");

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "This is not available write now", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "This is not available write now", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "This is not available write now", Toast.LENGTH_LONG).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // button click
    public void days(View view) {

        MyDB myDB = new MyDB(this);
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> courseCode = new ArrayList<>();
        ArrayList<String> roomNumber = new ArrayList<>();
        ArrayList<String> teacher = new ArrayList<>();


        // Sat
        if (view.getId() == R.id.sat) {
            viewRoutine("Saturday");
        }


        // Sun
        if (view.getId() == R.id.sun) {
            viewRoutine("Sunday");
        }

        // Mon
        if (view.getId() == R.id.mon) {
            viewRoutine("Monday");
        }

        // Tue
        if (view.getId() == R.id.tue) {
            viewRoutine("Tuesday");
        }

        // Wed
        if (view.getId() == R.id.wed) {
            viewRoutine("Wednesday");
        }

        // Thu
        if (view.getId() == R.id.thu) {
            viewRoutine("Thursday");
        }

        // Fri
        if (view.getId() == R.id.fri) {
            viewRoutine("Friday");
        }
    }


    public void viewRoutine(String day) {

        Cursor cursor = myDB.allSubjects(day);

        if (cursor.getCount() > 0) {

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                titles.add(cursor.getString(3));
                String s = cursor.getString(1) + "  To  " + cursor.getString(2);
                times.add(s);
                courseCode.add(cursor.getString(4));
                roomNumber.add(cursor.getString(5));
                teacher.add(cursor.getString(6));

            }

            Intent intent = new Intent(this, ExpListActivity.class);
            intent.putStringArrayListExtra("title", titles);
            intent.putStringArrayListExtra("time", times);
            intent.putStringArrayListExtra("code", courseCode);
            intent.putStringArrayListExtra("room", roomNumber);
            intent.putStringArrayListExtra("teacher", teacher);
            startActivity(intent);

            this.finish();

        } else {
            Toast.makeText(this, "No subject found", Toast.LENGTH_SHORT).show();
        }
    }


    public void getList(String day) {

        Cursor cursor = myDB.allSubjects(day);

        if (cursor.getCount() > 0) {

            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                titles.add(cursor.getString(3));
                String s = cursor.getString(1) + "  To  " + cursor.getString(2);
                times.add(s);
                courseCode.add(cursor.getString(4));
                roomNumber.add(cursor.getString(5));
                teacher.add(cursor.getString(6));
                id.add(cursor.getString(0));

            }


        }

        Intent intent = new Intent(this, ListAllSubjects.class);
        intent.putStringArrayListExtra("title", titles);
        intent.putStringArrayListExtra("time", times);
        intent.putStringArrayListExtra("id", id);

        // intent.putStringArrayListExtra("code", courseCode);
        // intent.putStringArrayListExtra("room", roomNumber);
        //  intent.putStringArrayListExtra("teacher", teacher);
        startActivity(intent);

        this.finish();

    }


}
