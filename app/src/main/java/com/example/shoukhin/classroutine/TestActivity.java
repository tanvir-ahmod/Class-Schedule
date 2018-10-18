package com.example.shoukhin.classroutine;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constants.ROUTINE);
        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tempdataSnapShot : dataSnapshot.getChildren()) {
                    RoutineModel routineModel = tempdataSnapShot.getValue(RoutineModel.class);
                    if (routineModel != null) {
                        for (int i = 0; i < Constants.DAY_ARRAY.length; i++) {
                            if (routineModel.getDay().equals(Constants.DAY_ARRAY[i])) {
                                Map<String, Object> stringObjectMap = new HashMap<>();
                                stringObjectMap.put(Constants.DAY_OF_WEEK, i);
                                databaseReference.child(tempdataSnapShot.getKey()).updateChildren(stringObjectMap);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
