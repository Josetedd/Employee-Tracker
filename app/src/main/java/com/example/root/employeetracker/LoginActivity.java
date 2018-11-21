package com.example.root.employeetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import helper.SqLiteHelper;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize views

        final EditText email = findViewById(R.id.edit_email);
        final EditText password = findViewById(R.id.edit_password);
        Button login = findViewById(R.id.button_login);
        TextView registerText = findViewById(R.id.register_text);


        // call userlogin method from SQLiteHelper class when login button is clicked

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqLiteHelper helper = new SqLiteHelper(LoginActivity.this);

                helper.userLogin(email.getText().toString(), password.getText().toString());
            }
        }); // end of login onclick listener

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, UserRegister.class);
                startActivity(i);
            }
        });
    } // end of onCreate method
}
