package com.example.ghostjoystick;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
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

        // Ask for overlay permission ONCE
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Intent i = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION
            );
            startActivity(i);
        }

        updateStatus();

        // TOGGLE INPUT
        toggleButton.setOnClickListener(v -> {
            GhostIME.ENABLED = !GhostIME.ENABLED;
            updateStatus();

            if (GhostIME.ENABLED) {
                startService(new Intent(this, FloatingFocusService.class));
            } else {
                stopService(new Intent(this, FloatingFocusService.class));
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

    private void updateStatus() {
        if (GhostIME.ENABLED) {
            statusText.setText("STATUS: INPUT ENABLED");
        } else {
            statusText.setText("STATUS: INPUT DISABLED");
        }
    }
}
