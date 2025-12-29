package com.joystickapp;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class WASDAccessibilityService extends AccessibilityService {

    public static WASDAccessibilityService instance;

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        instance = this;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        // Not needed
    }

    @Override
    public void onInterrupt() {
        // Not needed
    }

    // ===== PUBLIC API USED BY MAINACTIVITY =====

    public void press(int keyCode) {
        sendKey(KeyEvent.ACTION_DOWN, keyCode);
    }

    public void releaseAll() {
        int[] keys = {
                KeyEvent.KEYCODE_W,
                KeyEvent.KEYCODE_A,
                KeyEvent.KEYCODE_S,
                KeyEvent.KEYCODE_D
        };

        for (int key : keys) {
            sendKey(KeyEvent.ACTION_UP, key);
        }
    }

    // ===== INTERNAL SAFE KEY INJECTION =====

    private void sendKey(int action, int keyCode) {
        long now = System.currentTimeMillis();

        KeyEvent event = new KeyEvent(
                now,
                now,
                action,
                keyCode,
                0,
                0,
                KeyEvent.KEYCODE_UNKNOWN,
                0,
                KeyEvent.FLAG_FROM_SYSTEM,
                KeyEvent.KEYCODE_UNKNOWN
        );

        // IMPORTANT: AccessibilityService does NOT have dispatchKeyEvent()
        // We inject using this method instead:
        super.onKeyEvent(event);
    }
}
