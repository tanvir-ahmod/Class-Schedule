package com.example.shoukhin.classroutine;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {

    private DatabaseReference mFirebaseDatabase;
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("tag", "service Started");
        mFirebaseDatabase =  FirebaseDatabase.getInstance().getReference("asd");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String text = dataSnapshot.getValue(String.class);

                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                Log.d("tag", text);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


            return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("tag", "service stopped");
    }
}
