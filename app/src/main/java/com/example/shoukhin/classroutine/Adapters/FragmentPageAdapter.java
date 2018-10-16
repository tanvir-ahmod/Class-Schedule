package com.example.shoukhin.classroutine.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shoukhin.classroutine.Constants;
import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.RoutineFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;


public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    private Context context;
    private ArrayList<RoutineModel> routines = new ArrayList<>();

    public FragmentPageAdapter(FragmentManager fm, int numOfTabs, Context context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        //Log.d(Constants.LOGTAG, position + "");
        RoutineFragment routineFragment = new RoutineFragment();
        routineFragment.setContext(context);
        Bundle bundle = new Bundle();

        switch (position) {

            case 0:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(0));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 1:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(1));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 2:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(2));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 3:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(3));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 4:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(4));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 5:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(5));
                routineFragment.setArguments(bundle);
                return routineFragment;
            case 6:
                bundle.putParcelableArrayList(Constants.ROUTINE_MODEL, getRoutineOfSpecificDay(6));
                routineFragment.setArguments(bundle);
                return routineFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    public ArrayList<RoutineModel> getRoutines() {
        return routines;
    }

    public void setRoutines(ArrayList<RoutineModel> routines) {
        this.routines = new ArrayList<>(routines);
        //Log.d(Constants.LOGTAG, "routine size in fragmentPageAdpater:" + routines.size());
    }

    private ArrayList<RoutineModel> getRoutineOfSpecificDay(int dayCode) {
        ArrayList<RoutineModel> routine = new ArrayList<>();
        //Log.d(Constants.LOGTAG, "routine size in fragmentPageAdpater:" + routines.size());
        for (int i = 0; i < routines.size(); i++) {
            /*Log.d(Constants.LOGTAG, "daycode " + dayCode + " day + " + routines.get(i).getDay() + " "
                    + Constants.DAY_ARRAY[dayCode]);*/
            if (routines.get(i).getDay().equals(Constants.DAY_ARRAY[dayCode]))
                routine.add(routines.get(i));
        }
        sortByTime(routine);
        return routine;
    }

    private void sortByTime(ArrayList<RoutineModel> routine) {

        Collections.sort(routine, new Comparator<RoutineModel>() {
            @Override
            public int compare(RoutineModel lhs, RoutineModel rhs) {
                SimpleDateFormat format = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

                String timeLHS = lhs.getStartTime();
                String timeRHS = rhs.getStartTime();

                Calendar calendarRHD = Calendar.getInstance();
                Calendar calendarLHS = Calendar.getInstance();
                try {
                    calendarLHS.setTime(format.parse(timeLHS));
                    calendarRHD.setTime(format.parse(timeRHS));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                return calendarLHS.compareTo(calendarRHD);
            }

        });

    }
}
