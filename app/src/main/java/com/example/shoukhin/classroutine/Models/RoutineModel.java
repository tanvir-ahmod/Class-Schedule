package com.example.shoukhin.classroutine.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shoukhin on 16-Mar-17.
 */

public class RoutineModel implements Serializable, Parcelable {


    private String roomNumber;
    private String key;
    private String day;
    private String courseName;
    private String courseCode;
    private String startTime;
    private String endTime;
    private Date date;

    protected RoutineModel(Parcel in) {
        roomNumber = in.readString();
        key = in.readString();
        day = in.readString();
        courseName = in.readString();
        courseCode = in.readString();
        startTime = in.readString();
        endTime = in.readString();
    }

    public static final Creator<RoutineModel> CREATOR = new Creator<RoutineModel>() {
        @Override
        public RoutineModel createFromParcel(Parcel in) {
            return new RoutineModel(in);
        }

        @Override
        public RoutineModel[] newArray(int size) {
            return new RoutineModel[size];
        }
    };

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

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public RoutineModel() {
    }

    public RoutineModel(String day, String courseName, String courseCode, String startTime, String endTime, String roomNumber) {
        this.day = day;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomNumber = roomNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(roomNumber);
        dest.writeString(key);
        dest.writeString(day);
        dest.writeString(courseName);
        dest.writeString(courseCode);
        dest.writeString(startTime);
        dest.writeString(endTime);
    }
}
