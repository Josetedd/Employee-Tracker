package com.example.root.employeetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import helper.SqLiteHelper;

public class UserRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);


        // initialize views
       final EditText firstNameEdit = findViewById(R.id.first_name_edit);
        final EditText lastNameEdit = findViewById(R.id.last_name_edit);
        final EditText emailEdit = findViewById(R.id.email_edit);
        final EditText passwordEdit = findViewById(R.id.password_edit);
        final EditText confirmPassEdit = findViewById(R.id.confirm_edit);
        Button registerBtn = findViewById(R.id.register_button);


        // when register button is clicked
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if password and confirm password are the same


                if(!passwordEdit.getText().toString().equals(confirmPassEdit.getText().toString())){
                    passwordEdit.setError("");
                    confirmPassEdit.setError("Password doesnt match");
                }
                else{
                    // save the records into the db
                    SqLiteHelper helper = new SqLiteHelper(UserRegister.this);

                    helper.insertUser(firstNameEdit.getText().toString(),
                            lastNameEdit.getText().toString(),
                            emailEdit.getText().toString(),
                            passwordEdit.getText().toString());
                }

            }
        });




    } // end of onCreate
} // end of class
