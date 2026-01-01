package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ToggleOverlayService extends Service {

    private WindowManager wm;
    private View bubble;
    private boolean enabled = false;

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(2, createNotification());

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;

        bubble = LayoutInflater.from(this)
                .inflate(R.layout.toggle_overlay, null);

        Button btn = bubble.findViewById(R.id.toggle_btn);

        btn.setOnClickListener(v -> toggle(btn));

        wm.addView(bubble, params);
    }

    private void toggle(Button btn) {
        if (!enabled) {
            startService(new Intent(this, FocusOverlayService.class));
            btn.setText("❌");
            enabled = true;
        } else {
            stopService(new Intent(this, FocusOverlayService.class));
            btn.setText("🎮");
            enabled = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bubble != null) wm.removeView(bubble);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        String channelId = "toggle_overlay";

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(
                    channelId,
                    "Ghost Toggle",
                    NotificationManager.IMPORTANCE_MIN
            );
            getSystemService(NotificationManager.class)
                    .createNotificationChannel(ch);
        }

        return new Notification.Builder(this, channelId)
                .setContentTitle("Ghost Joystick")
                .setContentText("Toggle active")
                .setSmallIcon(android.R.drawable.ic_media_play)
                .build();
    }
}
