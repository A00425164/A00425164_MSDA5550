package com.example.user.myapplication;

import android.app.DatePickerDialog;
//import android.content.ContentValues;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;



//import static com.example.user.myapplication.InClassDatabaseHelper.DB_NAME;
//import static com.example.user.myapplication.InClassDatabaseHelper.TABLE_PERSON;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText date;

    String name;
    String password;
    String hcn;
    String date2;
    static String usrname;

    public static String getUsrName(){
        return usrname;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = findViewById(R.id.DateOfBirth);
        date.setOnClickListener(this);

        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        //SQLiteDatabase db = helper.getWritableDatabase();


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
//        SQLiteDatabase db = helper.getWritableDatabase();

        //run a query
//        Cursor cursor = db.query(InClassDatabaseHelper.TABLE_PERSON, new String[]
//                        {"NAME","PASSWORD","DATE"},
//                null,null,null,null,null);
//
//        if(cursor.moveToFirst()){
//
//        }



        EditText results0 =findViewById(R.id.Username);
        EditText results1 =findViewById(R.id.Name);
        EditText results2 =findViewById(R.id.Password);
        EditText results3 =findViewById(R.id.DateOfBirth);
        EditText results4 =findViewById(R.id.HealthCardNumber);

        usrname = results0.getText().toString();
        name = results1.getText().toString();
        password = results2.getText().toString();
        date2 = results3.getText().toString();
        hcn = results4.getText().toString();

//        ContentValues personValues = new ContentValues();
//        personValues.put("NAME",name);
//        personValues.put("PASSWORD",password);
//        personValues.put("HEALTH_CARD_NUMB",hcn);
//        personValues.put("DATE",date);
//
//        db.insert(TABLE_PERSON, null, personValues);
//
//        cursor.close(); //cleanup
//        db.close(); //cleanup

        if(     usrname.trim().equals("") ||
                name.trim().equals("") ||
                password.trim().equals("") ||
                date2.trim().equals("") ||
                hcn.trim().equals("")
                ){

            Toast.makeText(this, "All fields are mandatory. Please fill all the fields before continuing!",
                    Toast.LENGTH_LONG).show();
        }else{
            helper.addPerson(usrname, name, password, hcn, date2);

            Intent intent = new Intent(this, CalculateBMI.class);
            startActivity(intent);
        }



    }
}
