package com.example.shoukhin.classroutine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    int numOtabs;

    public FragmentPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOtabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        position = position % 3;
        //Log.d(Constants.LOGTAG, position + "");

        switch (position) {
            case 0:

                return new RoutineFragment();
            case 1:
                return new RoutineFragment();
            case 2:
                return new RoutineFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOtabs;
    }
}
