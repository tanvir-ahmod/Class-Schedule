package com.example.shoukhin.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class ViewNotification extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;

    TextView pinnedTbx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);

        initialize();
    }

    private void initialize() {

        pinnedTbx = (TextView) findViewById(R.id.view_noti_pin_tbx);

        mFirebaseDatabase =  FirebaseDatabase.getInstance().getReference("Notice Board").child("notice");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NotificationAndPinnedPost pinnedPost = dataSnapshot.getValue(NotificationAndPinnedPost.class);
                pinnedTbx.setText(pinnedPost.getPost());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
