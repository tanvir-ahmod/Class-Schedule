package com.example.shoukhin.classroutine;

import android.app.Dialog;
import android.app.TimePickerDialog;

import java.util.Calendar;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteRoutine extends AppCompatActivity {

    private int currentHour, currentMinute;
    private String key;

    ImageButton nextDay, previousDay;

    EditText courseName, courseCode, roomNumber;
    TextView fromTime, toTime, day;

    private DatabaseReference mFirebaseDatabase;

    int TIME_DIALOUGE_ID = 0;
    int position;
    boolean isFromTimeClicked = false;
    boolean isToTimeClicked = false;
    boolean isEditable = false;
    String storedKey;

    FirebaseAuth auth;

    private String[] dayArray = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    Button save, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_routine);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            // User is not logged in
            Toast.makeText(this, "Please Login first!", Toast.LENGTH_SHORT);
            finish();
        }

        initialize();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            RoutineStructure routine = (RoutineStructure) extras.get("routine");
            courseName.setText(routine.getCourseName());
            courseCode.setText(routine.getCourseCode());
            roomNumber.setText(routine.getRoomNumber());
            fromTime.setText(routine.getStartTime());
            toTime.setText(routine.getEndTime());

            isEditable = true;

            storedKey = routine.getKey();

            position = (int) extras.get("day");
            day.setText(dayArray[position]);
            delete.setVisibility(View.VISIBLE); //delete button is visibled
        }

        fromTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOUGE_ID); // Time Picker Will be Shown

                //since only one dialouge is created, flags required to determine which textview should be updated
                isFromTimeClicked = true;
                isToTimeClicked = false;


            }
        });

        toTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                showDialog(TIME_DIALOUGE_ID); // Time Picker Will be Shown

                //since only one dialouge is created, flags required to determine which textview should be updated
                isFromTimeClicked = false;
                isToTimeClicked = true;

            }
        });

        nextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;

                if (position >= dayArray.length)
                    position = 0;

                day.setText(dayArray[position]);
            }
        });

        previousDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;

                if (position < 0)
                    position = dayArray.length - 1;

                day.setText(dayArray[position]);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tempDay = day.getText().toString();
                String tempCourseCode = courseCode.getText().toString();
                String tempCourseName = courseName.getText().toString();
                String tempFromTime = fromTime.getText().toString();
                String temoToTime = toTime.getText().toString();
                String tempRoomNumber = roomNumber.getText().toString();

                RoutineStructure routine = new RoutineStructure(tempDay, tempCourseName, tempCourseCode, tempFromTime, temoToTime, tempRoomNumber);

                //saving to firebase database

                //if editable, just update the data
                if (isEditable == true) {
                    key = storedKey;
                } else {
                    //store as new data
                    key = mFirebaseDatabase.push().getKey();
                    //isEditable = true; //next save will just overwrite the data

                }

                //save it
                mFirebaseDatabase.child(key).setValue(routine);
                Toast.makeText(WriteRoutine.this, "saved Successfully", Toast.LENGTH_SHORT).show();
                finish();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(
                        WriteRoutine.this);
                alert.setCancelable(false);
                alert.setTitle("Alert!!");
                alert.setMessage("Are you sure to delete record?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference("routine").child(storedKey).removeValue();
                        Toast.makeText(WriteRoutine.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        finish();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alert.create();

                alertDialog.show();
            }
        });
    }

    private void initialize() {

        save = (Button) findViewById(R.id.saveRoutineBtn);
        delete = (Button) findViewById(R.id.add_delete_btn);
        delete.setVisibility(View.GONE); //initially invisible

        day = (TextView) findViewById(R.id.addDayTBx);

        courseName = (EditText) findViewById(R.id.addCourseNameEdtx);
        courseCode = (EditText) findViewById(R.id.addCourseCodeEdtx);
        roomNumber = (EditText) findViewById(R.id.addRoomNumEdtx);

        fromTime = (TextView) findViewById(R.id.addfromtimetbx);
        toTime = (TextView) findViewById(R.id.addtotimetbx);

        nextDay = (ImageButton) findViewById(R.id.addNextDayIbtn);
        previousDay = (ImageButton) findViewById(R.id.addPreviousDayIbtn);

        Calendar calendar = Calendar.getInstance();
        position = calendar.get(Calendar.DAY_OF_WEEK);

        //Day will be saturday
        if (position == 7)
            position = 0;

        day.setText(dayArray[position]);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("routine");
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new TimePickerDialog(this, mTimeSetListener, currentHour,
                        currentMinute, false);

            default:
                break;
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            currentHour = hourOfDay;
            currentMinute = minute;

            updateText(hourOfDay, minute);
        }
    };

    private void updateText(int hours, int mins) {

        String timeSet = "", hourString = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12) {
            hours = 12;
            timeSet = "PM";
        } else
            timeSet = "AM";

        if (hours > 0 && hours < 10) {
            hourString = "0" + String.valueOf(hours);
        } else
            hourString = String.valueOf(hours);

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hourString).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        if (isFromTimeClicked == true) {
            fromTime.setText(aTime);
        } else if (isToTimeClicked == true) {
            toTime.setText(aTime);
        }
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
