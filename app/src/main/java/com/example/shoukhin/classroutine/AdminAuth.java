package com.example.shoukhin.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AdminAuth extends AppCompatActivity {

    EditText email, password;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_auth);

        email = (EditText) findViewById(R.id.email_edtx);
        password = (EditText) findViewById(R.id.password_edtx);

        login = (Button) findViewById(R.id.login_button);

    }
}
