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

    public void sendKey(int key) {
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, key));
        dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, key));
    }

    @Override public void onAccessibilityEvent(AccessibilityEvent e) {}
    @Override public void onInterrupt() {}
}
