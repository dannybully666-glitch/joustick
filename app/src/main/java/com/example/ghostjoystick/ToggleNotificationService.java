package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ToggleNotificationService extends Service {

    private static final String CHANNEL_ID = "ghost_joystick";

    @Override
    public void onCreate() {
        super.onCreate();

        createChannel();

        Notification notification =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Ghost Joystick")
                        .setContentText("Tap to open keyboard")
                        .setSmallIcon(android.R.drawable.stat_sys_input_method)
                        .setOngoing(true)
                        .build();

        // MUST be here for Infinix
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Ghost Joystick",
                            NotificationManager.IMPORTANCE_LOW
                    );
            getSystemService(NotificationManager.class)
                    .createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
