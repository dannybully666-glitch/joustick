package com.example.ghostjoystick;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class FloatingFocusService extends Service {

    private WindowManager wm;
    private ImageView button;
    private EditText focusView;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        int type = Build.VERSION.SDK_INT >= 26
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                : WindowManager.LayoutParams.TYPE_PHONE;

        // Floating button
        button = new ImageView(this);
        button.setImageResource(android.R.drawable.ic_input_add);
        button.setAlpha(0.8f);

        WindowManager.LayoutParams btnParams =
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        type,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
        btnParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;

        wm.addView(button, btnParams);

        // Invisible focus view
        focusView = new EditText(this);
        focusView.setAlpha(0f);
        focusView.setWidth(1);
        focusView.setHeight(1);

        WindowManager.LayoutParams focusParams =
                new WindowManager.LayoutParams(
                        1,
                        1,
                        type,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        PixelFormat.TRANSLUCENT
                );
        focusParams.gravity = Gravity.START | Gravity.TOP;

        button.setOnClickListener(v -> {
            try {
                wm.addView(focusView, focusParams);
                focusView.requestFocus();
                focusView.postDelayed(() -> wm.removeView(focusView), 200);
            } catch (Exception ignored) {}
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            wm.removeView(button);
        } catch (Exception ignored) {}
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
