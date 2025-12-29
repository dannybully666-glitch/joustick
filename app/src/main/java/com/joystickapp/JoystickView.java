package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private Paint basePaint;
    private Paint hatPaint;

    private float centerX, centerY;
    private float baseRadius, hatRadius;
    private float hatX, hatY;

    // Key states
    private boolean wDown, aDown, sDown, dDown;

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setColor(Color.DKGRAY);

        hatPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hatPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2f;
        centerY = h / 2f;

        baseRadius = Math.min(w, h) * 0.45f;
        hatRadius = baseRadius * 0.4f;

        hatX = centerX;
        hatY = centerY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Base circle
        canvas.drawCircle(centerX, centerY, baseRadius, basePaint);

        // Knob
        canvas.drawCircle(hatX, hatY, hatRadius, hatPaint);
    }

    private void sendKey(int keyCode, boolean pressed) {
        if (JoystickIME.instance != null) {
            JoystickIME.instance.sendKey(keyCode, pressed);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        float dx = x - centerX;
        float dy = y - centerY;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        if (event.getAction() != MotionEvent.ACTION_UP) {

            if (distance < baseRadius) {
                hatX = x;
                hatY = y;
            } else {
                float ratio = baseRadius / distance;
                hatX = centerX + dx * ratio;
                hatY = centerY + dy * ratio;
            }

            float nx = dx / baseRadius;
            float ny = dy / baseRadius;

            boolean up = ny < -0.5f;
            boolean down = ny > 0.5f;
            boolean left = nx < -0.5f;
            boolean right = nx > 0.5f;

            if (up != wDown) sendKey(KeyEvent.KEYCODE_W, up);
            if (down != sDown) sendKey(KeyEvent.KEYCODE_S, down);
            if (left != aDown) sendKey(KeyEvent.KEYCODE_A, left);
            if (right != dDown) sendKey(KeyEvent.KEYCODE_D, right);

            wDown = up;
            sDown = down;
            aDown = left;
            dDown = right;

        } else {
            // Finger released → reset
            hatX = centerX;
            hatY = centerY;

            sendKey(KeyEvent.KEYCODE_W, false);
            sendKey(KeyEvent.KEYCODE_A, false);
            sendKey(KeyEvent.KEYCODE_S, false);
            sendKey(KeyEvent.KEYCODE_D, false);

            wDown = aDown = sDown = dDown = false;
        }

        invalidate();
        return true;
    }
}
