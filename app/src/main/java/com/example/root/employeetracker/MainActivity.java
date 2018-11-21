package com.example.root.employeetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import helper.SqLiteHelper;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sessionPref; //declare a sharedpreference for sessions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //---------------------------*** Check if user is logged in ***-----------------
        sessionPref = getSharedPreferences("sessionPref", MODE_PRIVATE);
        String email = sessionPref.getString("email", "");
        if (email.equals("")) {
            // show login activity
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        } else {


//----------------------------***Dispay Records***------------------------

            // declare and initialize Textview

            TextView showEmployees = findViewById(R.id.empRecords);

            // get the Record from the Db using SQLiteHelper readEmployee method
            SqLiteHelper helper = new SqLiteHelper(MainActivity.this); //create an instance of Sqlite helper class

            StringBuilder empRecords = helper.readEmployee();

            // set the records to be displayed in showEmployees Text view

            showEmployees.setText(empRecords.toString());

//----------------------------*** FAB***------------------------------------

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, AddEmployee.class);
                    startActivity(i);

                }
            });
        } // end of if statement for sessions

    } //end of oncreate method

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

        } else if (id == R.id.action_refresh_list) { //refreshes the Main Activity
            finish();
            startActivity(getIntent());
        } else if (id == R.id.action_logout) { //logs out by clearing session sharedpreference and opening login activity
            SharedPreferences.Editor editor = sessionPref.edit();
            editor.clear();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }
}
