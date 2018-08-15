package com.example.shoukhin.classroutine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoukhin.classroutine.Models.RoutineModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class ViewDates extends AppCompatActivity {
    private ArrayList<RoutineModel> allData;
    private ArrayList<RoutineModel> listOfRoutine;

    ListView listView;
    private BaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dates);


        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }

        initialize();

        RoutineModel routine = (RoutineModel) extras.get("selectedRoutine");
        allData = (ArrayList<RoutineModel>) extras.get("allData");

        for (int i = 0; i < allData.size(); i++) {
            RoutineModel tempRoutine = allData.get(i);
            String tempCourseCode = routine.getCourseCode();
            String tempCourseName = routine.getCourseName();

            //if course code matches
            if (tempCourseCode != null && !TextUtils.isEmpty(tempCourseCode) && tempCourseCode.equals(tempRoutine.getCourseCode())) {
                getDateFromDay(tempRoutine);

                //course name match check
            } else if (tempCourseName != null && !TextUtils.isEmpty(tempCourseName) && tempCourseName.equals(tempRoutine.getCourseName())) {
                //Log.d(ViewRoutine.LOGTAG, tempRoutine.getDay());
                getDateFromDay(routine);

            }

        }

        sortByDate();
        for (int i = 0; i < listOfRoutine.size(); i++) {
            Log.d(ViewRoutine.LOGTAG, listOfRoutine.get(i).getDay());
        }


    }

    private void initialize() {
        listView = (ListView) findViewById(R.id.date_listview);
        listOfRoutine = new ArrayList<>();

        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return listOfRoutine.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
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

                String tempRoomNo = "Date : " + listOfRoutine.get(position).getRoomNumber() + " (" + listOfRoutine.get(position).getDay() + ")";

                courseName.setText(listOfRoutine.get(position).getCourseName());
                courseCode.setText(listOfRoutine.get(position).getCourseCode());
                roomNumber.setText(tempRoomNo);
                fromTime.setText(listOfRoutine.get(position).getStartTime());
                toTime.setText(listOfRoutine.get(position).getEndTime());

                return convertView;
            }
        };

        listView.setAdapter(adapter);
    }

    private void getDateFromDay(final RoutineModel routine) {

        String day = routine.getDay();
        int searchedDay = Arrays.asList(ViewRoutine.dayArray).indexOf(day);
        Calendar calendar = Calendar.getInstance();
        int currentDate = calendar.get(Calendar.DATE);
        //Log.d(ViewRoutine.LOGTAG, "Current Date " + currentDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

        for (int i = currentDate; i <= daysInMonth; i++) {
            calendar.set(year, month, i);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


            if (searchedDay == dayOfWeek) {

                RoutineModel tempRoutine = new RoutineModel(routine.getDay(), routine.getCourseName(), routine.getCourseCode(), routine.getStartTime(), routine.getEndTime(), routine.getRoomNumber());
                String tempDate =  formatter.format(calendar.getTime());
                tempRoutine.setRoomNumber( tempDate);
                tempRoutine.setDate(calendar.getTime());
                listOfRoutine.add(tempRoutine);
            }
        }
    }

    private void sortByDate() {

        Collections.sort(listOfRoutine, new Comparator<RoutineModel>() {
            @Override
            public int compare(RoutineModel lhs, RoutineModel rhs) {

                Calendar calendarRHD = Calendar.getInstance();
                Calendar calendarLHS = Calendar.getInstance();

                calendarLHS.setTime(lhs.getDate());
                calendarRHD.setTime(rhs.getDate());

                //Log.d(ViewRoutine.LOGTAG, "lhs " + lhs.getDate() + " rhs " + rhs.getDate());

                return calendarLHS.compareTo(calendarRHD);
            }

        });

    }
}
