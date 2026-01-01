package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class GhostIME extends InputMethodService {

    private static GhostIME instance;

    // Controlled by overlay / toggle
    public static boolean ENABLED = false;

    public static GhostIME getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (instance == this) instance = null;
    }

    @Override
    public View onCreateInputView() {
        return null; // NO visible keyboard
    }

    @Override
    public void onStartInput(EditorInfo info, boolean restarting) {
        super.onStartInput(info, restarting);
    }

    // ===============================
    // METHODS REQUIRED BY KeyboardView
    // ===============================

    public static void keyDown(int keyCode) {
        if (!ENABLED) return;
        if (instance == null) return;

        InputConnection ic = instance.getCurrentInputConnection();
        if (ic == null) return;

        long t = System.currentTimeMillis();
        ic.sendKeyEvent(new KeyEvent(t, t, KeyEvent.ACTION_DOWN, keyCode, 0));
    }

    public static void keyUp(int keyCode) {
        if (!ENABLED) return;
        if (instance == null) return;

        InputConnection ic = instance.getCurrentInputConnection();
        if (ic == null) return;

        long t = System.currentTimeMillis();
        ic.sendKeyEvent(new KeyEvent(t, t, KeyEvent.ACTION_UP, keyCode, 0));
    }
}
