package com.example.root.employeetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import helper.SqLiteHelper;

public class AddEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        // Initialize views
        final EditText empName = findViewById(R.id.empName);                      // edittext with employee name
        final EditText empLocation = findViewById(R.id.empLocation);              // edittext with employee Location
        final EditText empDesignation = findViewById(R.id.empDesignation);        // edittext with employee Designation
        Button saveBtn = findViewById(R.id.saveBtn);                        // save button

        // create a button listener for the save button

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check user input
                if(empName.getText().length()==0){
                    empName.setError("Empty Name");

                }
                else if (empLocation.getText().length()==0){
                    empLocation.setError("Empty Location");
                }
                else if (empDesignation.getText().length()==0){
                    empDesignation.setError("Empty Designation");

                }
                else {

                    // save the data on sqlite by calling the sqlitehelper

                    // create a new instance of sqlite helper
                    SqLiteHelper helper = new SqLiteHelper(AddEmployee.this);

                    // insert the input using the insertEmployee method of the helper class
                    helper.insertEmployee(empName.getText().toString(),
                                            empLocation.getText().toString(),
                                            empDesignation.getText().toString());

                    // clear text in the inputs
                    empName.setText("");
                    empLocation.setText("");
                    empDesignation.setText("");

                }// end of if statement
            }
        });
    }
}
