package com.example.shoukhin.classroutine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

public class ViewNotification extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase, notificationDatabase;

    TextView pinnedTbx;

    private BaseAdapter adapter;
    private ArrayList<NotificationAndPinnedPost> notifications;

    private ListView viewNotification;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize();


        //pinned post read operation
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                NotificationAndPinnedPost pinnedPost = dataSnapshot.getValue(NotificationAndPinnedPost.class);
                pinnedTbx.setText(pinnedPost.getPost());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        notificationDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                notifications.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    NotificationAndPinnedPost notification = postSnapshot.getValue(NotificationAndPinnedPost.class);
                    notification.setKey(postSnapshot.getKey());

                    notifications.add(notification);
                }

                sortByDate();
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                auth = FirebaseAuth.getInstance();
                if (auth.getCurrentUser() != null) {
                    // User is not logged in, he can modify data
                    NotificationAndPinnedPost notification = notifications.get(position);

                    Intent intent = new Intent(ViewNotification.this, WriteNotification.class);
                    intent.putExtra("notification", notification);
                    startActivity(intent);
                }
            }
        });


    }

    private void sortByDate() {

        Collections.sort(notifications, new Comparator<NotificationAndPinnedPost>() {
            @Override
            public int compare(NotificationAndPinnedPost lhs, NotificationAndPinnedPost rhs) {
                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                /*String dateLHS = lhs.getTime();
                String dateRHS = rhs.getTime();*/

                Calendar calendarRHD = Calendar.getInstance();
                Calendar calendarLHS = Calendar.getInstance();
                try {
                    calendarLHS.setTimeInMillis(lhs.getTime());
                    calendarRHD.setTimeInMillis(rhs.getTime());

                } catch (Exception e) {
                    e.printStackTrace();
                }


                return calendarRHD.compareTo(calendarLHS);
            }

        });

    }

    private void initialize() {

        pinnedTbx = (TextView) findViewById(R.id.view_noti_pin_tbx);

        viewNotification = (ListView) findViewById(R.id.view_notification_listview);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("Notice Board").child("notice");
        notificationDatabase = FirebaseDatabase.getInstance().getReference("Notification");

        notifications = new ArrayList<>();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return notifications.size();
            }

            @Override
            public Object getItem(int position) {
                return notifications.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                if (convertView == null) {
                    convertView = inflater.inflate(R.layout.notification_listview, null);
                }

                TextView post = (TextView) convertView.findViewById(R.id.noti_listview_post_tbx);
                TextView time = (TextView) convertView.findViewById(R.id.noti_listview_time_tbx);

                post.setText(notifications.get(position).getPost());

                //calculation post arrival time
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(notifications.get(position).getTime());

                String tempTime = calendar.get(Calendar.DATE) + " " + getMonthForInt(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR)
                        + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + " ";

                //showing if time is AM or PM
                int a = calendar.get(Calendar.AM_PM);
                if (a == Calendar.AM) {
                    tempTime += "AM";

                } else
                    tempTime += "PM";

                time.setText(tempTime);


                return convertView;
            }
        };

        viewNotification.setAdapter(adapter);

    }


    private String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
