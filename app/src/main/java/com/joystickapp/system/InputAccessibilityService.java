package com.joystickapp.system;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.view.accessibility.AccessibilityEvent;

public class InputAccessibilityService extends AccessibilityService {

    public static InputAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        instance = this;
    }

    /**
     * Simulate a tap at screen coordinates (x, y)
     */
    public void tap(float x, float y) {
        if (instance == null) return;

        Path path = new Path();
        path.moveTo(x, y);

        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, 50);

        GestureDescription gesture = new GestureDescription.Builder()
                .addStroke(stroke)
                .build();

        dispatchGesture(gesture, null, null);
    }

    /**
     * Simulate a swipe
     */
    public void swipe(float x1, float y1, float x2, float y2, long duration) {
        if (instance == null) return;

        Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);

        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, duration);

        GestureDescription gesture = new GestureDescription.Builder()
                .addStroke(stroke)
                .build();

        dispatchGesture(gesture, null, null);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {}

    @Override
    public void onInterrupt() {}
}
