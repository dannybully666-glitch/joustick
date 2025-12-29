package com.joystickapp;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.Build;
import android.view.accessibility.AccessibilityEvent;

public class WASDAccessibilityService extends AccessibilityService {

    // 🔹 Global instance (THIS FIXES YOUR ERROR)
    public static WASDAccessibilityService instance;

    private static final int SWIPE_DISTANCE = 180;
    private static final int SWIPE_DURATION = 40;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not used
    }

    @Override
    public void onInterrupt() {
        // Not used
    }

    // ===== PUBLIC MOVEMENT API =====

    public void pressW() { swipe(0, -SWIPE_DISTANCE); }
    public void pressS() { swipe(0, SWIPE_DISTANCE); }
    public void pressA() { swipe(-SWIPE_DISTANCE, 0); }
    public void pressD() { swipe(SWIPE_DISTANCE, 0); }

    // ===== INTERNAL GESTURE =====

    private void swipe(int dx, int dy) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return;

        int cx = getResources().getDisplayMetrics().widthPixels / 2;
        int cy = getResources().getDisplayMetrics().heightPixels / 2;

        Path path = new Path();
        path.moveTo(cx, cy);
        path.lineTo(cx + dx, cy + dy);

        GestureDescription.StrokeDescription stroke =
                new GestureDescription.StrokeDescription(path, 0, SWIPE_DURATION);

        GestureDescription gesture =
                new GestureDescription.Builder()
                        .addStroke(stroke)
                        .build();

        dispatchGesture(gesture, null, null);
    }
}
