package com.joystickapp.overlay;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatingJoystickView extends View {

    private final float radius = 150f;
    private final PointF center = new PointF(radius, radius);
    private final PointF current = new PointF(center.x, center.y);

    private JoystickListener listener;

    public FloatingJoystickView(Context context) {
        super(context);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                (int) (radius * 2),
                (int) (radius * 2),
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                android.graphics.PixelFormat.TRANSLUCENT
        );

        setLayoutParams(params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float dx = e.getX() - center.x;
        float dy = e.getY() - center.y;

        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        if (dist > radius) {
            dx = dx / dist * radius;
            dy = dy / dist * radius;
        }

        current.set(center.x + dx, center.y + dy);

        if (listener != null)
            listener.onMove(dx / radius, dy / radius);

        if (e.getAction() == MotionEvent.ACTION_UP && listener != null) {
            listener.onMove(0, 0);
        }
        return true;
    }

    public void setListener(JoystickListener l) {
        listener = l;
    }

    public interface JoystickListener {
        void onMove(float x, float y);
    }
}
