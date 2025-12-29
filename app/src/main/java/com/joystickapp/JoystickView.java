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
        void onMove(float x, float y);
    }

    private OnMoveListener listener;

    public void setOnMoveListener(OnMoveListener l) {
        listener = l;
    }

    private Paint basePaint;
    private Paint hatPaint;

    private float centerX, centerY;
    private float baseRadius, hatRadius;
    private float hatX, hatY;

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

        // Hat / knob
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
        } else {
            // Return to center
            hatX = centerX;
            hatY = centerY;
        }

        // NORMALIZED OUTPUT (-1.0 to 1.0)
        if (listener != null) {
            listener.onMove(
                    (hatX - centerX) / baseRadius,
                    (hatY - centerY) / baseRadius
            );
        }

        invalidate();
        return true;
    }
}
