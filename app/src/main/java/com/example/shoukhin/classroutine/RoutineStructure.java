package com.example.shoukhin.classroutine;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shoukhin on 16-Mar-17.
 */

public class RoutineStructure implements Serializable {


    private String roomNumber;
    private String key;
    private String day;
    private String courseName;
    private String courseCode;
    private String startTime;
    private String endTime;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getKey() {
        return key;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public RoutineStructure() {
    }

    public RoutineStructure(String day, String courseName, String courseCode, String startTime, String endTime, String roomNumber) {
        this.day = day;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
    }

}
