package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

public class GhostIME extends InputMethodService {

    // Active IME instance
    private static GhostIME instance;

    // Logical toggle (controlled by overlay button)
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

    // UI not required
    @Override
    public View onCreateInputView() {
        return null;
    }

    @Override
    public void onStartInput(EditorInfo info, boolean restarting) {
        super.onStartInput(info, restarting);
    }

    // =========================================================
    // HARDWARE-STYLE KEY EVENTS (LEGAL IME API)
    // =========================================================

    public static void sendKey(int keyCode) {
        if (!ENABLED) return;

        GhostIME ime = instance;
        if (ime == null) return;

        InputConnection ic = ime.getCurrentInputConnection();
        if (ic == null) return;

        long now = System.currentTimeMillis();

        ic.sendKeyEvent(new KeyEvent(now, now,
                KeyEvent.ACTION_DOWN, keyCode, 0));

        ic.sendKeyEvent(new KeyEvent(now, now,
                KeyEvent.ACTION_UP, keyCode, 0));
    }

    public static void keyDown(int keyCode) {
        if (!ENABLED) return;

        GhostIME ime = instance;
        if (ime == null) return;

        InputConnection ic = ime.getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                KeyEvent.ACTION_DOWN,
                keyCode,
                0
        ));
    }

    public static void keyUp(int keyCode) {
        if (!ENABLED) return;

        GhostIME ime = instance;
        if (ime == null) return;

        InputConnection ic = ime.getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                KeyEvent.ACTION_UP,
                keyCode,
                0
        ));
    }
}
