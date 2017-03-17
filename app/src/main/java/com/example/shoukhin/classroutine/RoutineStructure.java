package com.example.shoukhin.classroutine;

/**
 * Created by Shoukhin on 16-Mar-17.
 */

public class RoutineStructure {


    private String roomNumber;
    private String key;
    private String day;
    private String courseName;
    private String courseCode;
    private String startTime;
    private String endTime;

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
