package com.joystickapp.system;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class InputAccessibilityService extends AccessibilityService {

    public static InputAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        instance = this;
    }

    public void sendKey(int keyCode) {
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyCode));
    }

    @Override public void onAccessibilityEvent(AccessibilityEvent event) {}
    @Override public void onInterrupt() {}
}
