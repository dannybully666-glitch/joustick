package com.joystickapp.overlay;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.os.IBinder;

import com.joystickapp.input.InputMapper;

public class FloatingJoystickService extends Service {

    WindowManager wm;
    FloatingJoystickView joystick;

    @Override
    public int onStartCommand(Intent i, int f, int id) {
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        joystick = new FloatingJoystickView(this);

        joystick.setListener(InputMapper::mapJoystick);

        WindowManager.LayoutParams p =
                new WindowManager.LayoutParams(
                        300, 300,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );

        wm.addView(joystick, p);
        return START_STICKY;
    }

    @Override public IBinder onBind(Intent i) { return null; }
}
