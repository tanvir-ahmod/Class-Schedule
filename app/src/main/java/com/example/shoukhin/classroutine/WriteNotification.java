package com.example.shoukhin.classroutine;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteNotification extends AppCompatActivity {

    TextView modifiedTime;
    boolean isEditable = false;

    String storedKey;

    EditText text;

    private DatabaseReference mFirebaseDatabase;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text = (EditText) findViewById(R.id.write_noti_edtx);

        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            NotificationAndPinnedPost notification = (NotificationAndPinnedPost) extras.get("notification");
            text.setText(notification.getPost());

            isEditable = true;

            storedKey = notification.getKey();
        }

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            // User is not logged in
            Toast.makeText(this, "Please Login first!", Toast.LENGTH_SHORT);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.write_notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.write_notification_save) {

            if(text.getText().toString().equals(""))
            {
                Toast.makeText(WriteNotification.this, "Enter valid data!", Toast.LENGTH_SHORT).show();
                return true;
            }

            NotificationAndPinnedPost notification = new NotificationAndPinnedPost(text.getText().toString());

            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Notification");

            if(isEditable){
                mFirebaseDatabase.child(storedKey).setValue(notification);
            }
            else {
                String key = mFirebaseDatabase.push().getKey();
                mFirebaseDatabase.child(key).setValue(notification);
            }

            Toast.makeText(WriteNotification.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            finish();

            //Log.d("tag","saved");
        }
        else if(id == R.id.write_notification_pinned){
            if(text.getText().toString().equals(""))
            {
                Toast.makeText(WriteNotification.this, "Enter valid data!", Toast.LENGTH_SHORT).show();
                return true;
            }

            NotificationAndPinnedPost notification = new NotificationAndPinnedPost(text.getText().toString());

            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Notice Board");
            mFirebaseDatabase.child("notice").setValue(notification);
            Toast.makeText(WriteNotification.this, "Saved As Pinned Post", Toast.LENGTH_SHORT).show();
        }

        else if(id == R.id.write_notification_delete) {
            AlertDialog.Builder alert = new AlertDialog.Builder(
                    WriteNotification.this);
            alert.setCancelable(false);
            alert.setTitle("Alert!!");
            alert.setMessage("Are you sure to delete record?");
            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FirebaseDatabase.getInstance().getReference("Notification").child(storedKey).removeValue();
                    Toast.makeText(WriteNotification.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    finish();

                }
            });
            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alert.create();
            alertDialog.show();
        }

        else if(id == android.R.id.home) {
                finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
