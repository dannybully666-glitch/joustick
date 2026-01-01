package com.example.ghostjoystick;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView statusText;
    private Button toggleButton;
    private Button selectKeyboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.status_text);
        toggleButton = findViewById(R.id.toggle_input);
        selectKeyboardButton = findViewById(R.id.select_keyboard);

        updateStatus();

        // TOGGLE INPUT BUTTON
        toggleButton.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(this)) {
                    requestOverlayPermission();
                    return;
                }
            }

            GhostIME.ENABLED = !GhostIME.ENABLED;
            updateStatus();

            if (GhostIME.ENABLED) {
                startFloatingService();
            } else {
                stopFloatingService();
            }
        });

        // SELECT KEYBOARD BUTTON
        selectKeyboardButton.setOnClickListener(v -> {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showInputMethodPicker();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // User returned from overlay settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (Settings.canDrawOverlays(this) && GhostIME.ENABLED) {
                startFloatingService();
            }
        }
    }

    private void updateStatus() {
        if (GhostIME.ENABLED) {
            statusText.setText("STATUS: INPUT ENABLED");
        } else {
            statusText.setText("STATUS: INPUT DISABLED");
        }
    }

    private void startFloatingService() {
        startService(new Intent(this, FloatingFocusService.class));
    }

    private void stopFloatingService() {
        stopService(new Intent(this, FloatingFocusService.class));
    }

    private void requestOverlayPermission() {
        Intent intent = new Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + getPackageName())
        );
        startActivity(intent);
    }
}
