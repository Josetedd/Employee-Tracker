package com.example.root.employeetracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import helper.SqLiteHelper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 //---------------------------*** Check if user is logged in ***-----------------
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = sharedPreferences.getString("email", "");
        if(email.equals("")){
            // show login activity
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();
        }


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

    }

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

        }
        else if(id == R.id.action_refresh_list){ //refreshes the Main Activity
            finish();
            startActivity(getIntent());
        }

        return super.onOptionsItemSelected(item);
    }
}
