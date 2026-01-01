package com.example.ghostjoystick;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class KeyboardView extends LinearLayout {

    public KeyboardView(Context context) {
        super(context);
    }

    public void bind(View root) {
        bindKey(root, R.id.key_w, KeyEvent.KEYCODE_W);
        bindKey(root, R.id.key_a, KeyEvent.KEYCODE_A);
        bindKey(root, R.id.key_s, KeyEvent.KEYCODE_S);
        bindKey(root, R.id.key_d, KeyEvent.KEYCODE_D);
    }

    private void bindKey(View root, int id, int keyCode) {
        Button b = root.findViewById(id);

        b.setOnTouchListener((v, e) -> {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                GhostIME.keyDown(keyCode);
            } else if (e.getAction() == MotionEvent.ACTION_UP ||
                       e.getAction() == MotionEvent.ACTION_CANCEL) {
                GhostIME.keyUp(keyCode);
            }
            return true;
        });
    }
}
