package com.joystickapp;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {

    private Paint basePaint, hatPaint;
    private float cx, cy, baseR, hatR, hx, hy;

    public JoystickView(Context c, AttributeSet a) {
        super(c, a);
        basePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basePaint.setColor(Color.DKGRAY);
        hatPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hatPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        cx = w / 2f;
        cy = h / 2f;
        baseR = Math.min(w, h) * 0.45f;
        hatR = baseR * 0.4f;
        hx = cx;
        hy = cy;
    }

    @Override
    protected void onDraw(Canvas c) {
        c.drawCircle(cx, cy, baseR, basePaint);
        c.drawCircle(hx, hy, hatR, hatPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float dx = e.getX() - cx;
        float dy = e.getY() - cy;

        float angle = (float) Math.toDegrees(Math.atan2(-dy, dx));

        String dir = "CENTER";

        if (e.getAction() != MotionEvent.ACTION_UP) {
            if (angle >= -45 && angle < 45) dir = "RIGHT";
            else if (angle >= 45 && angle < 135) dir = "UP";
            else if (angle >= -135 && angle < -45) dir = "DOWN";
            else dir = "LEFT";

            ((MainActivity) getContext()).onDirection(dir);
        }

        invalidate();
        return true;
    }
}
