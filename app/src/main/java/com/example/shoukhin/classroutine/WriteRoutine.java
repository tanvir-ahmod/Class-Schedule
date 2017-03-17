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

public class WriteRoutine extends AppCompatActivity {

    private int currentHour, currentMinute;

    EditText day, courseName, courseCode, roomNumber;
    TextView fromTime, toTime;

    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_routine);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void initialize()
    {

        save = (Button) findViewById(R.id.saveRoutineBtn);

        courseName = (EditText) findViewById(R.id.addCourseNameEdtx);
        courseCode = (EditText) findViewById(R.id.addCourseCodeEdtx);
        roomNumber = (EditText) findViewById(R.id.addRoomNumEdtx);
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
