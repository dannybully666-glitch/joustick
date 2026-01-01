package com.example.ghostjoystick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ask overlay permission if missing
        if (!Settings.canDrawOverlays(this)) {
            Intent i = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
            );
            startActivity(i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!started && Settings.canDrawOverlays(this)) {
            started = true;

            // 🔥 THIS IS THE CRITICAL LINE
            startForegroundService(
                    new Intent(this, ToggleOverlayService.class)
            );

            finish(); // app closes by design
        }
    }
}
