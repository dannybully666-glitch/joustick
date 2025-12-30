package com.example.joystickapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.example.joystickapp.overlay.FloatingOverlayService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Overlay permission
        if (!Settings.canDrawOverlays(this)) {
            startActivity(new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
            ));
        }

        // Open keyboard settings (like GK+ Step 1)
        startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));

        // Start overlay
        startService(new Intent(this, FloatingOverlayService.class));

        finish();
    }
}
