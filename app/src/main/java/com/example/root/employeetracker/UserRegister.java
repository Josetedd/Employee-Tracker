package com.example.root.employeetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class UserRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);


        // initialize views
        EditText firstNameEdit = findViewById(R.id.first_name_edit);
        EditText lastNameEdit = findViewById(R.id.last_name_edit);
        EditText emailEdit = findViewById(R.id.email_edit);
        EditText passwordEdit = findViewById(R.id.password_edit);
        EditText confirmPassEdit = findViewById(R.id.confirm_edit);

        // check if password and confirm password are the same
        if(passwordEdit.getText()!=confirmPassEdit){
            passwordEdit.setError("");
            confirmPassEdit.setError("Password doesnt match");
        }
        else{
            // save the records into the db
        }


    }
}
