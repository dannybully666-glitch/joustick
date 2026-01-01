package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

import androidx.core.app.NotificationCompat;

public class ToggleNotificationService extends Service {

    private static final String CHANNEL_ID = "ghost_joystick_channel";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannel();

        Notification notification = buildNotification();
        startForeground(1, notification);
    }

    private Notification buildNotification() {
        Intent toggleIntent = new Intent(this, ToggleReceiver.class);
        PendingIntent togglePending = PendingIntent.getBroadcast(
                this,
                0,
                toggleIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Ghost Joystick")
                .setContentText("Tap to show keyboard")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setOngoing(true)
                .addAction(
                        android.R.drawable.ic_input_add,
                        "Show Keyboard",
                        togglePending
                )
                .build();
    }

    private void createChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Ghost Joystick",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
