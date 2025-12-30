package com.example.joystickapp.overlay;

import android.app.Service;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.KeyEvent;

import com.example.joystickapp.R;
import com.example.joystickapp.ime.GameKeyboardIME;

public class FloatingOverlayService extends Service {

    private WindowManager wm;
    private View view;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        view = LayoutInflater.from(this)
                .inflate(R.layout.overlay_button, null);

        WindowManager.LayoutParams params =
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );

        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;

        view.setOnClickListener(v ->
                GameKeyboardIME.sendKey(KeyEvent.KEYCODE_W)
        );

        wm.addView(view, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (view != null) wm.removeView(view);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
