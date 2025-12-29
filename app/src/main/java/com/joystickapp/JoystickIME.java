package com.joystickapp;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.KeyCharacterMap;

public class JoystickIME extends InputMethodService {

    public static JoystickIME instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public void sendKey(int keyCode, boolean down) {
        if (instance == null) return;

        long now = System.currentTimeMillis();
        KeyEvent event = new KeyEvent(
                now,
                now,
                down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP,
                keyCode,
                0,
                0,
                KeyCharacterMap.VIRTUAL_KEYBOARD,
                0,
                0
        );

        getCurrentInputConnection().sendKeyEvent(event);
    }
}
