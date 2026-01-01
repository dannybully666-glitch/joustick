package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.LayoutInflater;

public class GhostIME extends InputMethodService {

    @Override
    public View onCreateInputView() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View keyboard = inflater.inflate(R.layout.ime_keyboard, null);

        KeyboardView helper = new KeyboardView(this, this);
        helper.bind(keyboard);

        return keyboard;
    }

    void sendDown(int keyCode) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
    }

    void sendUp(int keyCode) {
        InputConnection ic = getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyCode));
    }
}
