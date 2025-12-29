package com.joystickapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class JoystickView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public JoystickView(Context context) {
        super(context);
        init();
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setColor(0xFF444444);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float r = Math.min(getWidth(), getHeight()) / 2f - 10;
        canvas.drawCircle(getWidth()/2f, getHeight()/2f, r, paint);
    }
}
