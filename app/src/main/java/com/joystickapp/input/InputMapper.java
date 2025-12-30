package com.joystickapp.input;

import android.view.KeyEvent;

import com.joystickapp.system.InputAccessibilityService;

public class InputMapper {

    public static void mapJoystick(float x, float y) {
        if (x > 0.5f) send(KeyEvent.KEYCODE_DPAD_RIGHT);
        if (x < -0.5f) send(KeyEvent.KEYCODE_DPAD_LEFT);
        if (y > 0.5f) send(KeyEvent.KEYCODE_DPAD_DOWN);
        if (y < -0.5f) send(KeyEvent.KEYCODE_DPAD_UP);
    }

    private static void send(int key) {
        if (InputAccessibilityService.instance != null)
            InputAccessibilityService.instance.sendKey(key);
    }
}
