package com.joystickapp;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class JoystickIME extends InputMethodService {

    private static JoystickIME instance;

    public static JoystickIME get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public View onCreateInputView() {
        return getLayoutInflater().inflate(R.layout.activity_main, null);
    }

    public void sendKey(int keyCode, boolean down) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(
                new KeyEvent(
                        down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP,
                        keyCode
                )
        );
    }
}
