package com.joystickapp;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class JoystickIME extends InputMethodService {

    // Static instance so JoystickView can access IME
    public static JoystickIME instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public View onCreateInputView() {
        // No keyboard UI needed
        return null;
    }

    /**
     * Sends a real keyboard event to the system.
     * This is what games like Rain World detect.
     */
    public void sendKey(int keyCode, boolean pressed) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        long now = System.currentTimeMillis();

        KeyEvent event = new KeyEvent(
                now,
                now,
                pressed ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP,
                keyCode,
                0,
                0,
                KeyCharacterMap.VIRTUAL_KEYBOARD,
                0,
                KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE
        );

        ic.sendKeyEvent(event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }
}
