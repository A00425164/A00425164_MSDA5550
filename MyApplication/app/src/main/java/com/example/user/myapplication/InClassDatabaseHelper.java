package com.example.user.myapplication;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.Date;


class InClassDatabaseHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "in-class"; //name of the DB
    public static final int DB_VERSION = 1; //version of the DB
    public static final String TABLE_PERSON = "PERSON"; //name of the Person Table
    public static final String TABLE_HISTORY = "HISTORY"; //name of the History Table

    public InClassDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);//null is for cursors
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //db.deleteDatabase(DB_NAME);

        String sqlPerson = "CREATE TABLE " + TABLE_PERSON + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME TEXT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT, " //Never store passwords in clear text in real apps
                + "HEALTH_CARD_NUMB TEXT, "
                + "DATE TEXT);";

        db.execSQL(sqlPerson);


        String sqlHistory = "CREATE TABLE " + TABLE_HISTORY + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "USERNAME TEXT, "
                + "HEIGHT TEXT, "
                + "WEIGHT TEXT, "
                + "BMI TEXT, "
                + "DATE TEXT);";

        db.execSQL(sqlHistory);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void addPerson(String USERNAME, String Name, String Password, String HCN, String Date){

        SQLiteDatabase db = this.getWritableDatabase();
        //Date today = new Date(); //we want to start with some initial data
        ContentValues personValues = new ContentValues();
        personValues.put("USERNAME",USERNAME);
        personValues.put("NAME",Name);
        personValues.put("PASSWORD",Password);
        personValues.put("HEALTH_CARD_NUMB",HCN);
        personValues.put("DATE",Date);

        db.insert(TABLE_PERSON, null, personValues);

    }

    public void addHistory(String USERNAME, String Height, String Weight, String BMI){

        SQLiteDatabase db = this.getWritableDatabase();
        Date today = new Date(); //we want to start with some initial data
        ContentValues historyValues = new ContentValues();
        historyValues.put("USERNAME",USERNAME);
        historyValues.put("HEIGHT",Height);
        historyValues.put("WEIGHT",Weight);
        historyValues.put("BMI",BMI);
        historyValues.put("DATE",today.getTime());

        db.insert(TABLE_HISTORY, null, historyValues);

    }

}
