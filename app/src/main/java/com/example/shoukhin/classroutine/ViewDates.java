package com.example.shoukhin.classroutine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class ViewDates extends AppCompatActivity {
    private ArrayList<RoutineStructure> allData;
    private ArrayList<RoutineStructure> listOfRoutine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dates);

        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            return;
        }


        RoutineStructure routine = (RoutineStructure) extras.get("selectedRoutine");
        allData = (ArrayList<RoutineStructure>) extras.get("allData");

        // Log.d(ViewRoutine.LOGTAG, "ok");

        listOfRoutine = new ArrayList<>();

        for (int i = 0; i < allData.size(); i++) {
            RoutineStructure tempRoutine = allData.get(i);
            String tempCourseCode = routine.getCourseCode();
            String tempCourseName = routine.getCourseName();

            if (tempCourseCode != null && !TextUtils.isEmpty(tempCourseCode) && tempCourseCode.equals(tempRoutine.getCourseCode())) {
                //listOfRoutine.add(tempRoutine);
                //Log.d(ViewRoutine.LOGTAG, tempRoutine.getDay());
                getDateFromDay(tempRoutine);

            }/* else if (tempCourseName != null && !TextUtils.isEmpty(tempCourseName) && tempCourseName.equals(tempRoutine.getCourseName())) {
                //Log.d(ViewRoutine.LOGTAG, tempRoutine.getDay());
               // getDateFromDay(routine);

            }*/

        }

        sortByDate();
        for (int i = 0; i < listOfRoutine.size(); i++) {
            Log.d(ViewRoutine.LOGTAG, listOfRoutine.get(i).getDay());
        }


    }

    private void getDateFromDay(final RoutineStructure routine) {

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

                RoutineStructure tempRoutine = new RoutineStructure(routine.getDay(), routine.getCourseName(), routine.getCourseCode(), routine.getStartTime(), routine.getEndTime(), routine.getEndTime());
                String tempDate = " (" + formatter.format(calendar.getTime()) + ")";
                tempRoutine.setDay(day + tempDate);
                tempRoutine.setDate(calendar);
                listOfRoutine.add(tempRoutine);
                //Log.d(ViewRoutine.LOGTAG, "i = " + i + " day + date " + day + tempDate);


            }
        }
    }

    private void sortByDate() {

        Collections.sort(listOfRoutine, new Comparator<RoutineStructure>() {
            @Override
            public int compare(RoutineStructure lhs, RoutineStructure rhs) {

                Calendar calendarRHD = rhs.getDate();
                Calendar calendarLHS = lhs.getDate();

                return calendarLHS.compareTo(calendarRHD);
            }

        });

    }
}
