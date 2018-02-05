package com.example.firoz.classroutine;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Firoz on 10/3/2017.
 */

public class MyDB extends SQLiteOpenHelper {

    public MyDB(Context context) {
        super(context, "info.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table Saturday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Sunday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Monday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Tuesday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Wednesday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Thursday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");
        sqLiteDatabase.execSQL("create table Friday (id integer primary key, fromTime text, toTime text, courseTitle text unique, courseCode text, roomNumber text, courseTeacher text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Saturday");
        sqLiteDatabase.execSQL("drop table if exists Sunday");
        sqLiteDatabase.execSQL("drop table if exists Monday");
        sqLiteDatabase.execSQL("drop table if exists Tuesday");
        sqLiteDatabase.execSQL("drop table if exists Wednesday");
        sqLiteDatabase.execSQL("drop table if exists Thursday");
        sqLiteDatabase.execSQL("drop table if exists Friday");

        onCreate(sqLiteDatabase);
    }


    public long saveData(String table, String fromTime, String toTime, String courseTitle, String courseCode, String roomNumber, String courseTeacher) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("fromTime", fromTime);
        values.put("toTime", toTime);
        values.put("courseTitle", courseTitle);
        values.put("courseCode", courseCode);
        values.put("roomNumber", roomNumber);
        values.put("courseTeacher", courseTeacher);

        return database.insert(table, null, values);


    }

    public Cursor allSubjects(String day) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + day, null);
        return cursor;
    }


    public int deleteSub(String table, String id) {

        SQLiteDatabase database = getWritableDatabase();

        try {
            //database.execSQL("delete from " + table + " where title = " + title);
            return database.delete(table, " id = ? ", new String[]{id});

        } catch (SQLException e) {
            return -1;
        }
    }

}
