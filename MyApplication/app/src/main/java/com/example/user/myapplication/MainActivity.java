package com.example.user.myapplication;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import java.util.Calendar;



import static com.example.user.myapplication.InClassDatabaseHelper.DB_NAME;
import static com.example.user.myapplication.InClassDatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.DateOfBirth);
        date.setOnClickListener(this);

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

    }


    @Override
    public void onClick(View view) {

        if (view == date) {
            final Calendar calndr = Calendar.getInstance();

            int yr = calndr.get(Calendar.YEAR);
            int mnth = calndr.get(Calendar.MONTH);
            int day = calndr.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            date.setText((monthOfYear+1) + "/" + (dayOfMonth) + "/" + year);

                        }
                    }, yr, mnth, day);
            datePickerDialog.show();
        }
    }





    public void onClickEnter(View view){
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        //run a query
        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_NAME, new String[]
                        {"NAME","PASSWORD","DATE"},
                null,null,null,null,null);

        if(cursor.moveToFirst()){
            String name = cursor.getString(0);
            String password = cursor.getString(3);
            int hcn = cursor.getInt(2);
            int date = cursor.getInt(1);

            EditText results1 = (EditText) findViewById(R.id.Name);
            EditText results2 = (EditText) findViewById(R.id.Password);
            EditText results3 = (EditText) findViewById(R.id.DateOfBirth);
            EditText results4 = (EditText) findViewById(R.id.HealthCardNumber);

            results1.setText(name);
            results2.setText(password);
            results3.setText(date);
            results4.setText(hcn);

            ContentValues personValues = new ContentValues();
            personValues.put("NAME",name);
            personValues.put("PASSWORD",password);
            personValues.put("HEALTH_CARD_NUMB",hcn);
            personValues.put("DATE",date);

            db.insert(TABLE_NAME, null, personValues);
        }

        cursor.close(); //cleanup
        db.close(); //cleanup

        Intent intent = new Intent(this,CalculateBMI.class);
        startActivity(intent);
    }
}
