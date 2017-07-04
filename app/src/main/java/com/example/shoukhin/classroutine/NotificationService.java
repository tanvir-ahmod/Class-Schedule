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

    public NotificationService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        showNotification( remoteMessage.getNotification().getBody(), "Tap to read");
    }

    private void showNotification(String message1, String message2) {
        Intent intnt = new Intent(this, ViewNotification.class);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intnt, 0);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification n = new Notification.Builder(this)
                .setContentTitle(message1).setContentText(message2)
                .setContentIntent(pIntent).setSmallIcon(R.drawable.addnotification)
                .setSound(soundUri)
                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(0, n);
    }
}
