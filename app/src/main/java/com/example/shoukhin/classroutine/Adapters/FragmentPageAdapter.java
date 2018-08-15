package com.example.shoukhin.classroutine.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.RoutineFragment;


public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;
    private Context context;

    public FragmentPageAdapter(FragmentManager fm, int numOfTabs, Context context) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        //Log.d(Constants.LOGTAG, position + "");
        RoutineFragment routineFragment = new RoutineFragment();
        switch (position) {

            case 0:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 1:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 2:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 3:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 4:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 5:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
            case 6:
                routineFragment = new RoutineFragment();
                routineFragment.setContext(context);
                return routineFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
