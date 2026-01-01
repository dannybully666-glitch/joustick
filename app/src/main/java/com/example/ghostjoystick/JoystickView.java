package com.example.ghostjoystick;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private int lastKey = -1;

    public JoystickView(Context c) {
        super(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX() - getWidth() / 2f;
        float y = e.getY() - getHeight() / 2f;

        if (e.getAction() == MotionEvent.ACTION_UP) {
            lastKey = -1;
            return true;
        }

        if (Math.abs(x) > Math.abs(y)) {
            lastKey = x > 0 ? KeyEvent.KEYCODE_D : KeyEvent.KEYCODE_A;
        } else {
            lastKey = y > 0 ? KeyEvent.KEYCODE_S : KeyEvent.KEYCODE_W;
        }

        GhostIME.sendKey(lastKey);
        return true;
    }
}
