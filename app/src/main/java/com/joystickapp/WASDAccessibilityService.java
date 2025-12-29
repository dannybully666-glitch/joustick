package com.joystickapp;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class WASDAccessibilityService extends AccessibilityService {

    public static WASDAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        instance = this;

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.FLAG_REQUEST_FILTER_KEY_EVENTS;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.eventTypes = AccessibilityEvent.TYPES_ALL_MASK;

        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not needed
    }

    @Override
    public void onInterrupt() {
    }

    // SEND KEY
    public void sendKey(int keyCode) {
        long now = System.currentTimeMillis();

        KeyEvent down = new KeyEvent(now, now, KeyEvent.ACTION_DOWN, keyCode, 0);
        KeyEvent up   = new KeyEvent(now, now, KeyEvent.ACTION_UP, keyCode, 0);

        dispatchKeyEvent(down);
        dispatchKeyEvent(up);
    }
}
