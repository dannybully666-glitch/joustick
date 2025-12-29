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

    private static final float DEADZONE = 0.25f; // 25% of radius
    private String lastDir = "CENTER";

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

        if (e.getAction() == MotionEvent.ACTION_UP) {
            reset();
            sendDir("CENTER");
            return true;
        }

        // Clamp knob inside base
        if (dist > baseR) {
            dx = dx / dist * baseR;
            dy = dy / dist * baseR;
        }

        hx = cx + dx;
        hy = cy + dy;

        float normX = dx / baseR;
        float normY = dy / baseR;

        // DEADZONE CHECK
        if (Math.abs(normX) < DEADZONE && Math.abs(normY) < DEADZONE) {
            sendDir("CENTER");
        } else {
            String dir = compute8Way(normX, normY);
            sendDir(dir);
        }

        invalidate();
        return true;
    }

    private void reset() {
        hx = cx;
        hy = cy;
        invalidate();
    }

    private String compute8Way(float x, float y) {
        if (x > 0.5f && Math.abs(y) < 0.5f) return "D";
        if (x < -0.5f && Math.abs(y) < 0.5f) return "A";
        if (y > 0.5f && Math.abs(x) < 0.5f) return "S";
        if (y < -0.5f && Math.abs(x) < 0.5f) return "W";

        if (x > 0 && y < 0) return "W+D";
        if (x < 0 && y < 0) return "W+A";
        if (x > 0 && y > 0) return "S+D";
        if (x < 0 && y > 0) return "S+A";

        return "CENTER";
    }

    private void sendDir(String dir) {
        if (!dir.equals(lastDir)) {
            lastDir = dir;
            ((MainActivity) getContext()).onDirection(dir);
        }
    }
            }
