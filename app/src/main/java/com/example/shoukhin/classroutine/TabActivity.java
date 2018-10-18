package com.example.shoukhin.classroutine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.example.shoukhin.classroutine.fragments.RoutineFragment;
import com.example.shoukhin.classroutine.utilities.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    private ArrayList<RoutineModel> routines = new ArrayList<>();
    private final int DAY_IN_WEEK = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("View Routine");
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
       /* for (int i = 0; i < DAY_IN_WEEK; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.EXTRA_DAY, i);
            RoutineFragment routineFragment = new RoutineFragment();
            routineFragment.setArguments(bundle);
            adapter.addFragment(routineFragment, Constants.DAY_ARRAY[i]);
        }*/

        //adapter.addFragment(new PendingFragment(), "Pending Orders");
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        DatabaseReference mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("routine");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                routines.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RoutineModel routine = postSnapshot.getValue(RoutineModel.class);
                    if (routine != null) {
                        routine.setKey(postSnapshot.getKey());

                        for (int i = 0; i < Constants.DAY_ARRAY.length; i++) {
                            if (routine.getDay().equals(Constants.DAY_ARRAY[i])) {

                            }
                        }
                    }
                    routines.add(routine);
                }
               // adapter.setRoutines(routines);
                viewPager.setAdapter(adapter);
                // Log.d(Constants.LOGTAG, "routine size : " + routines.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
