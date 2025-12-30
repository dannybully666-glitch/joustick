package com.example.joystickapp.input;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class InputAccessibilityService extends AccessibilityService {

    private static InputAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
    }

    public static void sendKeyStatic() {
        if (instance == null) return;

        // KEY DOWN
        instance.sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_W)
        );

        // KEY UP
        instance.sendKeyEvent(
                new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_W)
        );
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not needed for key injection
    }

    @Override
    public void onInterrupt() {
    }
}
