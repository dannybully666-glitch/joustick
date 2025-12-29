package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private Paint basePaint, hatPaint;
    private float centerX, centerY, baseRadius, hatRadius;
    private float hatX, hatY;

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);

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
        getParent().requestDisallowInterceptTouchEvent(true);

        float x = event.getX();
        float y = event.getY();

        float dx = x - centerX;
        float dy = y - centerY;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (event.getAction() == MotionEvent.ACTION_UP) {
            hatX = centerX;
            hatY = centerY;
            sendDirection("CENTER");
            invalidate();
            return true;
        }

        if (dist < baseRadius) {
            hatX = x;
            hatY = y;
        } else {
            float r = baseRadius / dist;
            hatX = centerX + dx * r;
            hatY = centerY + dy * r;
        }

        sendDirection(calcDirection(dx, dy));
        invalidate();
        return true;
    }

    private void sendDirection(String dir) {
        if (getContext() instanceof MainActivity) {
            ((MainActivity) getContext()).onDirection(dir);
        }
    }

    private String calcDirection(float dx, float dy) {
        if (Math.abs(dx) < 50 && Math.abs(dy) < 50) return "CENTER";
        if (Math.abs(dx) > Math.abs(dy)) {
            return dx > 0 ? "D" : "A";
        } else {
            return dy > 0 ? "S" : "W";
        }
    }
}
