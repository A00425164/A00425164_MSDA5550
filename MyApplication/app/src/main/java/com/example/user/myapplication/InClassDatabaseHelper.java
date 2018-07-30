package com.example.user.myapplication;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.util.Date;


class InClassDatabaseHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "in-class"; //name of the DB
    public static final int DB_VERSION = 1; //version of the DB
    public static final String TABLE_NAME = "PERSON"; //name of the Table

    public InClassDatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);//null is for cursors
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //context.deleteDatabase(in-class);

        String sql = "CREATE TABLE " + TABLE_NAME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "PASSWORD TEXT, " //Never store passwords in clear text in real apps
                + "HEALTH_CARD_NUMB INTEGER, "
                + "DATE INTEGER);";

        db.execSQL(sql);

        Date today = new Date(); //we want to start with some initial data
        ContentValues personValues = new ContentValues();
        personValues.put("NAME","Dan Penny");
        personValues.put("PASSWORD","Super Secret");
        personValues.put("HEALTH_CARD_NUMB","1234 5678 9101");
        personValues.put("DATE",today.getTime());

        db.insert("in-class", null, personValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
