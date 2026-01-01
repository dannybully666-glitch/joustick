package com.example.ghostjoystick;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class FloatingFocusService extends Service {

    private WindowManager windowManager;
    private View floatingButton;
    private EditText fakeInput;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        int layoutType = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                : WindowManager.LayoutParams.TYPE_PHONE;

        // Floating + button
        floatingButton = LayoutInflater.from(this)
                .inflate(R.layout.floating_button, null);

        WindowManager.LayoutParams buttonParams =
                new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        layoutType,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );

        buttonParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;

        windowManager.addView(floatingButton, buttonParams);

        floatingButton.setOnClickListener(v -> showKeyboard());
    }

    private void showKeyboard() {
        if (!GhostIME.ENABLED) return;

        if (fakeInput == null) {
            fakeInput = new EditText(this);
            fakeInput.setFocusable(true);
            fakeInput.setFocusableInTouchMode(true);
            fakeInput.setVisibility(View.INVISIBLE);

            WindowManager.LayoutParams inputParams =
                    new WindowManager.LayoutParams(
                            1,
                            1,
                            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                            PixelFormat.TRANSLUCENT
                    );

            inputParams.gravity = Gravity.START | Gravity.TOP;

            windowManager.addView(fakeInput, inputParams);
        }

        fakeInput.requestFocus();

        InputMethodManager imm =
                (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if (imm != null) {
            imm.showSoftInput(fakeInput, InputMethodManager.SHOW_FORCED);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (floatingButton != null) windowManager.removeView(floatingButton);
        if (fakeInput != null) windowManager.removeView(fakeInput);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
