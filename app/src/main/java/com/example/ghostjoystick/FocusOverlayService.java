package com.example.ghostjoystick;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.View;

public class FocusOverlayService extends Service {

    private WindowManager wm;
    private View overlay;
    private FocusEditText focusEdit;

    @Override
    public void onCreate() {
        super.onCreate();

        startForeground(1, createNotification());

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                1,
                1,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                PixelFormat.TRANSLUCENT
        );

        overlay = LayoutInflater.from(this)
                .inflate(R.layout.focus_overlay, null);

        focusEdit = overlay.findViewById(R.id.focus_edit);

        wm.addView(overlay, params);

        // 🔥 FORCE IME AFTER ATTACH
        overlay.post(() -> focusEdit.requestIme());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlay != null) wm.removeView(overlay);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private Notification createNotification() {
        String channelId = "focus_overlay";

        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel ch = new NotificationChannel(
                    channelId,
                    "Ghost Focus",
                    NotificationManager.IMPORTANCE_MIN
            );
            getSystemService(NotificationManager.class)
                    .createNotificationChannel(ch);
        }

        return new Notification.Builder(this, channelId)
                .setContentTitle("Ghost Joystick")
                .setContentText("Input focus active")
                .setSmallIcon(android.R.drawable.ic_input_add)
                .build();
    }
}
