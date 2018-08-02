package com.example.user.myapplication;

import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class CalculateBMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
    }

    public void calculate (View view){
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        //SQLiteDatabase db = helper.getWritableDatabase();


        EditText height = (EditText) findViewById(R.id.Height);
        String value1 = height.getText().toString();
        Double heightVal = Double.parseDouble(value1);
        System.out.println("Here is the height: "+heightVal);

        EditText weight = (EditText) findViewById(R.id.Weight);
        String value2 = weight.getText().toString();
        Double weightVal = Double.parseDouble(value2);
        System.out.println("Here is the weight: "+weightVal);

        Double calc = (weightVal/(heightVal * heightVal));
        EditText result = (EditText) findViewById(R.id.Result);


        result.setText(calc.toString());

        String Usrname = MainActivity.getUsrName();

        helper.addHistory(Usrname, heightVal.toString(), weightVal.toString(), calc.toString());



    }

    public void navHistory(View view){
        Intent intent = new Intent(this, BMIListActivity.class);
        intent.putExtra("username", getIntent().getStringExtra("username"));
        startActivity(intent);

    }
}
