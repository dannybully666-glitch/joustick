package com.example.ghostjoystick;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class OverlayService extends Service {

    private WindowManager wm;
    private View overlay;

    @Override
    public void onCreate() {
        super.onCreate();

        wm = (WindowManager) getSystemService(WINDOW_SERVICE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                PixelFormat.TRANSLUCENT
        );

        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;

        overlay = LayoutInflater.from(this)
                .inflate(R.layout.overlay_layout, null);

        wm.addView(overlay, params);

        // Force IME focus
        overlay.post(() -> {
            View ghost = overlay.findViewById(R.id.ghost_edit_text);
            ghost.requestFocus();
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(ghost, InputMethodManager.SHOW_FORCED);
        });

        // BUTTON → SEND W
        Button btn = overlay.findViewById(R.id.btn_move);
        btn.setOnClickListener(v -> {
            GhostIME.sendKey(android.view.KeyEvent.KEYCODE_W);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlay != null) wm.removeView(overlay);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
