package com.joystickapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;

public class JoystickOverlayService extends Service {

    private static JoystickOverlayService instance;
    private WindowManager wm;
    private JoystickView joystick;
    private boolean shown = false;

    public static void toggle() {
        if (instance != null) instance.toggleInternal();
    }

    private void toggleInternal() {
        if (shown) {
            wm.removeView(joystick);
        } else {
            wm.addView(joystick, params());
        }
        shown = !shown;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        joystick = new JoystickView(this);
    }

    private WindowManager.LayoutParams params() {
        return new WindowManager.LayoutParams(
                400,
                400,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
        );
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
