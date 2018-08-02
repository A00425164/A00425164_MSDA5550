package com.example.user.myapplication;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class BMIListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bmilist); //List activity does this for you!

        String user = getIntent().getStringExtra("username");
        ArrayList<String> results = getHistory(user);

        ListView listBMIResults = getListView();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_expandable_list_item_1, //<- Layout param for ListActivity
                results);
        listBMIResults.setAdapter(listAdapter);




    }

    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id){
        //System.out.println("Clicked on " + results[position].toString());
    }



    //BMIResult[] results = { new BMIResult(5.5,100), new BMIResult(4.3,156)};


    public ArrayList<String> getHistory(String user){

        InClassDatabaseHelper newHelper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = newHelper.getWritableDatabase();

        String whereClause = "USERNAME = ?";
        String[] whereArgs = new String[] {
                user
        };

        Cursor cursor = db.query("HISTORY", new String[] {"HEIGHT","WEIGHT","EDATE","BMI"}, whereClause,whereArgs,null,null,null);

        ArrayList<String> histList = new ArrayList<>();
        histList.add("BMI Entries by "+user);
        histList.add("Entry Date | Height | Weight | BMI");

        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                String height = cursor.getString(0);
                String weight = cursor.getString(1);
                String date = cursor.getString(2);
                String bmi = cursor.getString(3);

                BMIResult e = new BMIResult(height,weight,bmi,date);
                e.toCustomString();

            }
        }
        else {
            histList.add("No records added yet");
        }

        return histList;
    }



}
