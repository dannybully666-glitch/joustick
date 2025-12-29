package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private float centerX, centerY;
    private float baseRadius = 200;
    private float hatRadius = 80;
    private float hatX, hatY;

    private Paint basePaint = new Paint();
    private Paint hatPaint = new Paint();

    public JoystickView(Context context) {
        super(context);
        basePaint.setColor(0xFF888888);
        basePaint.setAntiAlias(true);

        hatPaint.setColor(0xFF000000);
        hatPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2f;
        centerY = h / 2f;
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

        if (event.getAction() != MotionEvent.ACTION_UP) {
            if (distance < baseRadius) {
                hatX = event.getX();
                hatY = event.getY();
            } else {
                hatX = centerX + (dx / distance) * baseRadius;
                hatY = centerY + (dy / distance) * baseRadius;
            }
        } else {
            hatX = centerX;
            hatY = centerY;
        }

        invalidate();
        return true;
    }
}
