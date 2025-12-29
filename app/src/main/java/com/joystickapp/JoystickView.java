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

    // REQUIRED for programmatic creation
    public JoystickView(Context context) {
        super(context);
        init();
    }

    // REQUIRED for XML inflation
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
        canvas.drawCircle(centerX, centerY, baseRadius, basePaint);
        canvas.drawCircle(hatX, hatY, hatRadius, hatPaint);
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
            sendWASD(dx, dy, true);
        } else {
            hatX = centerX;
            hatY = centerY;
            sendWASD(0, 0, false);
        }

        invalidate();
        return true;
    }

    private void sendWASD(float dx, float dy, boolean down) {
        JoystickIME ime = JoystickIME.get();
        if (ime == null) return;

        ime.sendKey(KeyEvent.KEYCODE_W, dy < -40 && down);
        ime.sendKey(KeyEvent.KEYCODE_S, dy > 40 && down);
        ime.sendKey(KeyEvent.KEYCODE_A, dx < -40 && down);
        ime.sendKey(KeyEvent.KEYCODE_D, dx > 40 && down);
    }
}
