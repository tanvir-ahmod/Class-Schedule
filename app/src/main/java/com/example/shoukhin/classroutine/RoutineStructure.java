package com.example.shoukhin.classroutine;

import java.io.Serializable;

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

    public void setKey(String key) {
        this.key = key;
    }

    public String getRoomNumber() {

        return roomNumber;
    }

    public String getKey() {
        return key;
    }

    public String getDay() {
        return day;
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
