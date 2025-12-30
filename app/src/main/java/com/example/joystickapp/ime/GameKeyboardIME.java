package com.example.joystickapp.ime;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class GameKeyboardIME extends InputMethodService {

    private static GameKeyboardIME instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static void sendKey(int keyCode) {
        if (instance == null) return;

        InputConnection ic = instance.getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyCode));
    }
}
