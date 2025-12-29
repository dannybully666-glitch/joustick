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
    private float cx, cy, baseR, hatR;
    private float hx, hy;

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
        float dist = (float) Math.sqrt(dx * dx + dy * dy);

        if (e.getAction() != MotionEvent.ACTION_UP) {
            if (dist < baseR) {
                hx = e.getX();
                hy = e.getY();
            } else {
                hx = cx + dx / dist * baseR;
                hy = cy + dy / dist * baseR;
            }

            String dir;
            if (Math.abs(dx) > Math.abs(dy))
                dir = dx > 0 ? "D" : "A";
            else
                dir = dy > 0 ? "S" : "W";

            ((MainActivity) getContext()).onDirection(dir);
        } else {
            hx = cx;
            hy = cy;
        }

        invalidate();
        return true;
    }
}
