package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private float centerX, centerY;
    private float baseRadius, hatRadius;
    private float hatX, hatY;

    private Paint basePaint;
    private Paint hatPaint;

    public JoystickView(Context context) {
        super(context);
        init();
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        baseRadius = Math.min(w, h) / 2.5f;
        hatRadius = baseRadius / 2f;

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
        float dx = event.getX() - centerX;
        float dy = event.getY() - centerY;

        float distance = (float) Math.sqrt(dx * dx + dy * dy);

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                if (distance < baseRadius) {
                    hatX = event.getX();
                    hatY = event.getY();
                } else {
                    float ratio = baseRadius / distance;
                    hatX = centerX + dx * ratio;
                    hatY = centerY + dy * ratio;
                }

                String dir = calcDirection(dx, dy);
                if (getContext() instanceof MainActivity) {
                    ((MainActivity) getContext()).onDirection(dir);
                }
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                hatX = centerX;
                hatY = centerY;

                if (getContext() instanceof MainActivity) {
                    ((MainActivity) getContext()).onDirection("CENTER");
                }
                invalidate();
                break;
        }

        return true;
    }

    // TRUE 8-WAY DIRECTION LOGIC
    private String calcDirection(float dx, float dy) {
        if (Math.abs(dx) < 20 && Math.abs(dy) < 20) {
            return "CENTER";
        }

        double angle = Math.toDegrees(Math.atan2(-dy, dx));
        if (angle < 0) angle += 360;

        if (angle >= 337.5 || angle < 22.5) return "D";
        if (angle < 67.5) return "WD";
        if (angle < 112.5) return "W";
        if (angle < 157.5) return "WA";
        if (angle < 202.5) return "A";
        if (angle < 247.5) return "SA";
        if (angle < 292.5) return "S";
        return "SD";
    }
}
