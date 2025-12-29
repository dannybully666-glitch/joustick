package com.joystickapp;

import android.accessibilityservice.AccessibilityService;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

public class WASDAccessibilityService extends AccessibilityService {

    // Static instance so MainActivity can access the service
    public static WASDAccessibilityService instance;

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

    // ---- Internal key sender ----
    private void sendKey(int keyCode, int action) {
        KeyEvent event = new KeyEvent(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                action,
                keyCode,
                0
        );
        dispatchKeyEvent(event);
    }

    // ---- Press keys ----
    public void pressW() {
        sendKey(KeyEvent.KEYCODE_W, KeyEvent.ACTION_DOWN);
    }

    public void pressA() {
        sendKey(KeyEvent.KEYCODE_A, KeyEvent.ACTION_DOWN);
    }

    public void pressS() {
        sendKey(KeyEvent.KEYCODE_S, KeyEvent.ACTION_DOWN);
    }

    public void pressD() {
        sendKey(KeyEvent.KEYCODE_D, KeyEvent.ACTION_DOWN);
    }

    // ---- Release all keys ----
    public void releaseAll() {
        sendKey(KeyEvent.KEYCODE_W, KeyEvent.ACTION_UP);
        sendKey(KeyEvent.KEYCODE_A, KeyEvent.ACTION_UP);
        sendKey(KeyEvent.KEYCODE_S, KeyEvent.ACTION_UP);
        sendKey(KeyEvent.KEYCODE_D, KeyEvent.ACTION_UP);
    }
}
