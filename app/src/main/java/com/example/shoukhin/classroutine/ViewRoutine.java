package com.example.shoukhin.classroutine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewRoutine extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView viewRoutine;
    BaseAdapter adapter;

    ArrayList<RoutineStructure> allData;
    ArrayList<RoutineStructure> currentDayData;

    private DatabaseReference mFirebaseDatabase;

    private String[] dayArray = {"Saturday", "Sunday", "Monday", "Thuesday", "Wednesday", "Thursday", "Friday"};

    int cuurrentDayPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routine);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        initialize();

        //getting all routine data from firebase database
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //loop through the child
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    RoutineStructure routine = postSnapshot.getValue(RoutineStructure.class);
                    routine.setKey(postSnapshot.getKey());

                    //storing all data into a arraylist
                    allData.add(routine);

                }


                //showing only selected day's routine
                showCurrentDayRoutine();

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showCurrentDayRoutine() {

        for(int i = 0; i < allData.size(); i++)
        {
            //if current selected day is matched then only display that day's data
            if(allData.get(i).getDay().equals(dayArray[cuurrentDayPosition]))
            {
                currentDayData.add(allData.get(i));
            }
        }


    }

    private void initialize() {

        //getting today's day number of the week
        Calendar calendar = Calendar.getInstance();
        cuurrentDayPosition = calendar.get(Calendar.DAY_OF_WEEK);

        mFirebaseDatabase =  FirebaseDatabase.getInstance().getReference("routine");

        viewRoutine = (ListView) findViewById(R.id.viewRoutine);
        viewRoutine.setOnTouchListener(new OnSwipeTouchListener(this)); //adding swipe listener to listview

        allData = new ArrayList<>();
        currentDayData = new ArrayList<>();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return currentDayData.size();
            }

            @Override
            public Object getItem(int position) {
                return currentDayData.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.listview, null);
                }

                TextView courseName = (TextView) convertView.findViewById(R.id.listviewCourseName);
                TextView courseCode = (TextView) convertView.findViewById(R.id.listviewCourseCode);
                TextView roomNumber = (TextView) convertView.findViewById(R.id.listviewRoomNumbertbx);
                TextView fromTime = (TextView) convertView.findViewById(R.id.listviewFromtimetbx);
                TextView toTime = (TextView) convertView.findViewById(R.id.listviewTotimetbx);

                courseName.setText(currentDayData.get(position).getCourseName());
                courseCode.setText(currentDayData.get(position).getCourseCode());
                roomNumber.setText(currentDayData.get(position).getRoomNumber());
                fromTime.setText(currentDayData.get(position).getStartTime());
                toTime.setText(currentDayData.get(position).getEndTime());


                return convertView;
            }
        };

        viewRoutine.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_routine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        else if(id == R.id.write_data){
            startActivity(new Intent(ViewRoutine.this, WriteRoutine.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
