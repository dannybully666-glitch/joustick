package com.joystickapp.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

public class FloatingButtonView extends Button {

    float lastX, lastY;

    public FloatingButtonView(Context c, String label) {
        super(c);
        setText(label);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            setX(getX() + e.getRawX() - lastX);
            setY(getY() + e.getRawY() - lastY);
        }
        lastX = e.getRawX();
        lastY = e.getRawY();
        return true;
    }
}
