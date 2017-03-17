package com.example.shoukhin.classroutine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(getBaseContext(), MyService.class));

        mFirebaseDatabase =  FirebaseDatabase.getInstance().getReference("asd");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text = dataSnapshot.getValue(String.class);

                //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();

               // Log.d("tag", text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
