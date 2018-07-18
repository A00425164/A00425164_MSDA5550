package com.example.user.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME, new String[]
                {"NAME","PASSWORD","DATE"},
                null,null,null,null,null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            String password = cursor.getString(1);
            int date = cursor.getInt(2);

            EditText results1 = (EditText) findViewById(R.id.Name);
            EditText results2 = (EditText) findViewById(R.id.Password);
            EditText results3 = (EditText) findViewById(R.id.DateOfBirth);
            results1.setText(name);
            results2.setText(password);
            results3.setText(date);
        }

        cursor.close(); //cleanup
        db.close(); //cleanup
    }



    public void onClickEnter(View view){
        Intent intent = new Intent(this,CalculateBMI.class);
        startActivity(intent);
    }
}
