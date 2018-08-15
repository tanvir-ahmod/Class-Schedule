package com.example.shoukhin.classroutine;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoukhin.classroutine.Adapters.RoutineAdapter;
import com.example.shoukhin.classroutine.Models.RoutineModel;

import java.util.ArrayList;

public class RoutineFragment extends Fragment {

    private ArrayList<RoutineModel> routines = new ArrayList<>();
    RecyclerView recyclerView;
    RoutineAdapter routineAdapter;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_routine, container, false);

        recyclerView = view.findViewById(R.id.routine_recycler_view);
        routineAdapter = new RoutineAdapter(routines, context);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(routineAdapter);

        RoutineModel routineModel = new RoutineModel();
        routineModel.setCourseName("a");
        routineModel.setCourseCode("a");
        routineModel.setRoomNumber("a");
        routineModel.setStartTime("a");
        routineModel.setEndTime("a");
        routineModel.setCourseName("a");

        routines.add(routineModel);

        routineAdapter.notifyDataSetChanged();


        // Inflate the layout for this fragment
        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
