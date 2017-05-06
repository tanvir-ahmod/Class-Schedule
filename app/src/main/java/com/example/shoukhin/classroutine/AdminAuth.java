package com.example.shoukhin.classroutine;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminAuth extends AppCompatActivity {

    EditText emailEdtx, passwordEdtx;

    Button login;

    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_auth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emailEdtx = (EditText) findViewById(R.id.email_edtx);
        passwordEdtx = (EditText) findViewById(R.id.password_edtx);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        login = (Button) findViewById(R.id.login_button);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(AdminAuth.this, AdminZone.class));
            finish();
        }

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdtx.getText().toString();
                final String password = passwordEdtx.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AdminAuth.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {

                                        Toast.makeText(AdminAuth.this, "Incorrect email or password!", Toast.LENGTH_LONG).show();

                                } else {
                                    Intent intent = new Intent(AdminAuth.this, AdminZone.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
