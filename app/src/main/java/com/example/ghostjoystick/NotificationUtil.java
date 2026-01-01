package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtil {

    public static final String CHANNEL_ID = "ghost_overlay";

    public static Notification create(Context ctx) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(
                    CHANNEL_ID,
                    "Ghost Overlay",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager nm = ctx.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(ch);
        }

        return new Notification.Builder(ctx, CHANNEL_ID)
                .setContentTitle("Ghost Joystick")
                .setContentText("Overlay running")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .build();
    }
}
