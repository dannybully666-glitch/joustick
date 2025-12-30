package com.joystickapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.WindowManager;

public class OverlayService extends Service {

    private WindowManager windowManager;
    private FloatingJoystickView joystickView;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        joystickView = new FloatingJoystickView(this);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                300,
                300,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );

        windowManager.addView(joystickView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (joystickView != null) {
            windowManager.removeView(joystickView);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
