package com.example.shoukhin.classroutine.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoukhin.classroutine.Adapters.RoutineAdapter;
import com.example.shoukhin.classroutine.Constants;
import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoutineFragment extends Fragment {


    @BindView(R.id.routine_recycler_view)
    RecyclerView recyclerView;

    RoutineAdapter routineAdapter;
    private static ArrayList<RoutineModel> allData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routine, container, false);
        ButterKnife.bind(this, view);
        initializeVariables();


        int day = 0;

        if (getArguments() != null)
            day = getArguments().getInt(Constants.EXTRA_DAY);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference().child(Constants.ROUTINE_MODEL);
        Query query = databaseReference.orderByChild(Constants.DAY).equalTo(Constants.DAY_ARRAY[day]);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot tempDataSnapShot : dataSnapshot.getChildren()) {
                    try {
                        RoutineModel routine = tempDataSnapShot.getValue(RoutineModel.class);
                        if (routine != null) {
                            routine.setKey(tempDataSnapShot.getKey());
                            allData.add(routine);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                routineAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return view;
    }

    private void initializeVariables() {
        routineAdapter = new RoutineAdapter(allData, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(routineAdapter);
    }
}
