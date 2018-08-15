package com.example.shoukhin.classroutine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.shoukhin.classroutine.Adapters.FragmentPageAdapter;
import com.example.shoukhin.classroutine.Models.RoutineModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {

    private ArrayList<RoutineModel> routines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabactivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Sat"));
        tabLayout.addTab(tabLayout.newTab().setText("Sun"));
        tabLayout.addTab(tabLayout.newTab().setText("Mon"));
        tabLayout.addTab(tabLayout.newTab().setText("Tue"));
        tabLayout.addTab(tabLayout.newTab().setText("Wed"));
        tabLayout.addTab(tabLayout.newTab().setText("Thu"));
        tabLayout.addTab(tabLayout.newTab().setText("Fri"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final FragmentPageAdapter adapter = new FragmentPageAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), this);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
                    }
                    routines.add(routine);
                }
                adapter.setRoutines(routines);
                viewPager.setAdapter(adapter);
               // Log.d(Constants.LOGTAG, "routine size : " + routines.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
