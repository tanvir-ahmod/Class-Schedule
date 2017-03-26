package com.example.shoukhin.classroutine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootTimeService extends BroadcastReceiver {
    public BootTimeService() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, RoutineService.class);
            context.startService(serviceIntent);
        }

    }
}
