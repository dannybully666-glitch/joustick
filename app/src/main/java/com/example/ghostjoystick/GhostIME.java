package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.LayoutInflater;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class GhostIME extends InputMethodService {

    private View keyboardView;

    @Override
    public View onCreateInputView() {
        keyboardView = LayoutInflater.from(this)
                .inflate(R.layout.ime_keyboard, null);

        KeyboardView helper = new KeyboardView(this, this);
        helper.bind(keyboardView);

        // 🔴 FORCE ATTACH
        setInputView(keyboardView);
        return keyboardView;
    }

    @Override
    public void onStartInputView(EditorInfo info, boolean restarting) {
        super.onStartInputView(info, restarting);

        // 🔴 FORCE SHOW IME WINDOW (OEM FIX)
        showWindow(true);

        if (keyboardView != null) {
            setInputView(keyboardView);
        }
    }

    @Override
    public boolean onEvaluateFullscreenMode() {
        // 🔴 NEVER fullscreen (prevents OEM hiding)
        return false;
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
