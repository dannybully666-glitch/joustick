package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    public interface OnMoveListener {
        void onMove(float angle, float strength);
    }

    private OnMoveListener moveListener;

    private Paint basePaint;
    private Paint hatPaint;

    private float centerX, centerY;
    private float baseRadius, hatRadius;
    private float hatX, hatY;

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setOnMoveListener(OnMoveListener listener) {
        this.moveListener = listener;
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

            float angle = (float) Math.toDegrees(Math.atan2(-dy, dx));
            if (angle < 0) angle += 360;
            float strength = Math.min(distance / baseRadius, 1f);

            if (moveListener != null) {
                moveListener.onMove(angle, strength);
            }

        } else {
            hatX = centerX;
            hatY = centerY;

            if (moveListener != null) {
                moveListener.onMove(-1, 0);
            }
        }

        invalidate();
        return true;
    }
}
