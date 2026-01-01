package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class GhostIME extends InputMethodService {

    private static GhostIME instance;
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
        View v = getLayoutInflater().inflate(R.layout.keyboard_wasd, null);

        bindKey(v, R.id.key_w, KeyEvent.KEYCODE_W);
        bindKey(v, R.id.key_a, KeyEvent.KEYCODE_A);
        bindKey(v, R.id.key_s, KeyEvent.KEYCODE_S);
        bindKey(v, R.id.key_d, KeyEvent.KEYCODE_D);

        return v;
    }

    private void bindKey(View root, int id, int keyCode) {
        Button b = root.findViewById(id);
        b.setOnTouchListener((v, e) -> {
            InputConnection ic = getCurrentInputConnection();
            if (ic == null || !ENABLED) return false;

            if (e.getAction() == android.view.MotionEvent.ACTION_DOWN) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
            } else if (e.getAction() == android.view.MotionEvent.ACTION_UP) {
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyCode));
            }
            return true;
        });
    }
}
