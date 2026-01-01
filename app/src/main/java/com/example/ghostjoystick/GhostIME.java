package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

/**
 * GhostIME
 * Acts like a software hardware-keyboard (GameKeyboard style)
 */
public class GhostIME extends InputMethodService {

    // 🔥 STATIC ACTIVE INSTANCE (CRITICAL)
    private static GhostIME instance;

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

    /**
     * This IME does NOT need a UI to function.
     * You can return null safely.
     */
    @Override
    public View onCreateInputView() {
        return null;
    }

    /**
     * Accept ALL input types
     */
    @Override
    public void onStartInput(EditorInfo info, boolean restarting) {
        super.onStartInput(info, restarting);
    }

    // =========================================================
    // 🔥 HARDWARE-STYLE KEY INJECTION (THIS IS THE MAGIC)
    // =========================================================

    public static void sendKey(int keyCode) {
        GhostIME ime = instance;
        if (ime == null) return;

        long now = System.currentTimeMillis();

        KeyEvent down = new KeyEvent(
                now,
                now,
                KeyEvent.ACTION_DOWN,
                keyCode,
                0
        );

        KeyEvent up = new KeyEvent(
                now,
                now,
                KeyEvent.ACTION_UP,
                keyCode,
                0
        );

        ime.sendKeyEvent(down);
        ime.sendKeyEvent(up);
    }

    /**
     * For HOLD / movement keys (WASD)
     */
    public static void keyDown(int keyCode) {
        GhostIME ime = instance;
        if (ime == null) return;

        KeyEvent down = new KeyEvent(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                KeyEvent.ACTION_DOWN,
                keyCode,
                0
        );

        ime.sendKeyEvent(down);
    }

    public static void keyUp(int keyCode) {
        GhostIME ime = instance;
        if (ime == null) return;

        KeyEvent up = new KeyEvent(
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                KeyEvent.ACTION_UP,
                keyCode,
                0
        );

        ime.sendKeyEvent(up);
    }
}
