package com.joystickapp;

import android.view.MotionEvent;
import android.util.Log;

public class InputTranslator {

    public static void translate(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        Log.d("Joystick", "X=" + x + " Y=" + y);

        // Later: convert to key events / HID / IME
    }
}
