package com.joystickapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.joystickapp.overlay.FloatingJoystickService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Build UI programmatically (cannot fail)
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(40, 40, 40, 40);

        Button overlayBtn = new Button(this);
        overlayBtn.setText("Start Joystick Overlay");
        overlayBtn.setOnClickListener(v ->
                startService(new Intent(this, FloatingJoystickService.class))
        );

        Button accessBtn = new Button(this);
        accessBtn.setText("Enable Accessibility Service");
        accessBtn.setOnClickListener(v ->
                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        );

        root.addView(overlayBtn);
        root.addView(accessBtn);

        setContentView(root);
    }
}
