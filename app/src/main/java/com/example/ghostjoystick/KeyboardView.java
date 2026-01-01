package com.example.ghostjoystick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class KeyboardView extends LinearLayout {

    public KeyboardView(Context context) {
        super(context);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    public void bindKey(View keyView, int keyCode) {
        keyView.setOnTouchListener((v, event) -> {

            if (!GhostIME.ENABLED) return true;

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    GhostIME.keyDown(keyCode);
                    return true;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    GhostIME.keyUp(keyCode);
                    return true;
            }

            return false;
        });
    }
}
