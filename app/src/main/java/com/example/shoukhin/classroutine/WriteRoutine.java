package com.example.shoukhin.classroutine;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteRoutine extends AppCompatActivity {

    private int currentHour, currentMinute;
    private String key;

    EditText day, courseName, courseCode, roomNumber;
    TextView fromTime, toTime;

    private DatabaseReference mFirebaseDatabase;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_routine);

        initialize();

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

                key = mFirebaseDatabase.push().getKey();
                mFirebaseDatabase.child(tempDay).child(key).setValue(routine);


            }
        });
    }

    private void initialize() {

        save = (Button) findViewById(R.id.saveRoutineBtn);

        courseName = (EditText) findViewById(R.id.addCourseNameEdtx);
        courseCode = (EditText) findViewById(R.id.addCourseCodeEdtx);
        roomNumber = (EditText) findViewById(R.id.addRoomNumEdtx);

        fromTime = (TextView) findViewById(R.id.addfromtimetbx);
        toTime = (TextView) findViewById(R.id.addtotimetbx);

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

        }
    };
}
