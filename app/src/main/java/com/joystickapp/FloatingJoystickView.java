package com.joystickapp;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class FloatingJoystickView extends FrameLayout {

    public FloatingJoystickView(Context context) {
        super(context);
        inflate(context, R.layout.overlay_joystick, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputTranslator.translate(event);
        return true;
    }
}
