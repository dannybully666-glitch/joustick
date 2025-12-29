package com.joystickapp;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class WASDAccessibilityService extends AccessibilityService {

    public static WASDAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
        Log.d("WASD", "Accessibility service connected");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not used
    }

    @Override
    public void onInterrupt() {
    }

    // CALLED FROM ACTIVITY
    public void onDirection(String dir) {
        Log.d("WASD", "Direction: " + dir);
    }
}
