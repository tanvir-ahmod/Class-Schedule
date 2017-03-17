package com.example.shoukhin.classroutine;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;

public class WriteRoutine extends AppCompatActivity {

    private int currentHour, currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_routine);
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
