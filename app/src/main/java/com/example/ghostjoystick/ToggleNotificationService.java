package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ToggleNotificationService extends Service {

    public static final String ACTION_TOGGLE = "ghostjoystick.TOGGLE";
    private static final String CHANNEL_ID = "ghost_toggle";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();
        startForeground(1001, buildNotification());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null && ACTION_TOGGLE.equals(intent.getAction())) {
            GhostIME.ENABLED = !GhostIME.ENABLED;
            updateNotification();
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification buildNotification() {
        String state = GhostIME.ENABLED ? "ON" : "OFF";
        String icon = GhostIME.ENABLED ? "❌" : "🎮";

        Intent toggleIntent = new Intent(this, ToggleNotificationService.class);
        toggleIntent.setAction(ACTION_TOGGLE);

        PendingIntent togglePI = PendingIntent.getService(
                this,
                0,
                toggleIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(icon + " Ghost Joystick — " + state)
                .setContentText("Tap to toggle input injection")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .setContentIntent(togglePI)
                .build();
    }

    private void updateNotification() {
        NotificationManager nm = getSystemService(NotificationManager.class);
        nm.notify(1001, buildNotification());
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID,
                    "Ghost Joystick Toggle",
                    NotificationManager.IMPORTANCE_LOW
            );
            getSystemService(NotificationManager.class)
                    .createNotificationChannel(ch);
        }
    }
}
