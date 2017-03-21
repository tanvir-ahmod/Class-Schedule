package com.example.shoukhin.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteNotification extends AppCompatActivity {

    TextView modifiedTime;

    EditText text;

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_notification);

        text = (EditText) findViewById(R.id.write_noti_edtx);

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
            String key = mFirebaseDatabase.push().getKey();
            mFirebaseDatabase.child(key).setValue(notification);

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
            //Log.d("tag", "pinned");
        }

        return super.onOptionsItemSelected(item);
    }
}
