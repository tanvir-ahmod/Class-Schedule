package com.example.shoukhin.classroutine;

import java.util.Calendar;

/**
 * Created by Shoukhin on 21-Mar-17.
 */

public class NotificationAndPinnedPost {
    public String post;
    public long time;

    public NotificationAndPinnedPost(String post) {
        this.post = post;
        time = Calendar.getInstance().getTimeInMillis();
    }

    public String getPost() {
        return post;
    }

    public long getTime() {
        return time;
    }
}
