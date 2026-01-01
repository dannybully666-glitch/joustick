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

    private static final String CHANNEL_ID = "ghost_joystick_channel";
    private static final int NOTIF_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        createChannel();

        Notification notification =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setContentTitle("Ghost Joystick")
                        .setContentText("Tap to open keyboard picker")
                        .setSmallIcon(android.R.drawable.ic_media_play)
                        .setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();

        // 🔥 MUST be called immediately on Infinix / Android 14
        startForeground(NOTIF_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Keep running
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Ghost Joystick",
                            NotificationManager.IMPORTANCE_LOW
                    );

            NotificationManager nm =
                    getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }
}
