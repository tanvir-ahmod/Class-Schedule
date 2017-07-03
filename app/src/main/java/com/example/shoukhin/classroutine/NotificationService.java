package com.example.shoukhin.classroutine;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG = "tag";

    public NotificationService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        showNotification( remoteMessage.getNotification().getBody(), "Tap to read");
    }

    private void showNotification(String message1, String messege2) {
        Intent intnt = new Intent(this, ViewNotification.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intnt, 0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        Notification n = new Notification.Builder(this)
                .setContentTitle(message1).setContentText(messege2)
                .setContentIntent(pIntent).setSmallIcon(R.drawable.addnotification)
                .setSound(soundUri)
                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, n);
    }
}
