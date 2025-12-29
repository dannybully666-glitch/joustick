package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class JoystickView extends View {

    Paint base = new Paint(), knob = new Paint();
    float cx, cy, bx, by, r = 150;

    public JoystickView(Context c) {
        super(c);
        base.setColor(Color.DKGRAY);
        knob.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        cx = cy = w / 2f;
        bx = by = cx;
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawCircle(cx, cy, r, base);
        c.drawCircle(bx, by, 60, knob);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float dx = e.getX() - cx;
        float dy = e.getY() - cy;

        bx = cx + dx;
        by = cy + dy;

        sendKeys(dx, dy);
        invalidate();

        if (e.getAction() == MotionEvent.ACTION_UP) {
            bx = by = cx;
            sendKeyUp();
        }

        return true;
    }

    private void sendKeys(float dx, float dy) {
        InputConnection ic = ((JoystickIME) JoystickIME.thisInstance).getCurrentInputConnection();
        if (ic == null) return;

        if (dx > 40) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_D));
        if (dx < -40) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_A));
        if (dy > 40) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_S));
        if (dy < -40) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_W));
    }

    private void sendKeyUp() {
        InputConnection ic = ((JoystickIME) JoystickIME.thisInstance).getCurrentInputConnection();
        if (ic == null) return;

        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_W));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_A));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_S));
        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_D));
    }
}
