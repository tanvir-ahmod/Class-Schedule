package com.example.shoukhin.classroutine;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by Shoukhin on 21-Mar-17.
 */

public class NotificationAndPinnedPost implements Serializable {

    public String key;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {

        return key;
    }

    public String post;
    public long time;

    public NotificationAndPinnedPost(String post) {
        this.post = post;
        time = Calendar.getInstance().getTimeInMillis();
    }

    public NotificationAndPinnedPost() {
    }

    public String getPost() {

        return post;
    }

    public long getTime() {
        return time;
    }
}
