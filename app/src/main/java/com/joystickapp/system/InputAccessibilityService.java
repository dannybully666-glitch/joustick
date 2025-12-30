package com.joystickapp.system;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.util.Log;

public class InputAccessibilityService extends AccessibilityService {

    public static InputAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        instance = this;
        Log.d("Joystick", "Accessibility service connected");
    }

    /**
     * TEMPORARY stub.
     * Real input will be done via dispatchGesture().
     */
    public void sendKey(int keyCode) {
        Log.d("Joystick", "sendKey requested: " + keyCode);
        // NO direct key injection here (not allowed on modern Android)
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}

    @Override
    public void onInterrupt() {}
}
