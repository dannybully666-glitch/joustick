package com.joystickapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        // Request overlay permission
        if (!Settings.canDrawOverlays(this)) {
            Intent i = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(i);
        }

        // Start overlay service
        startService(new Intent(this, JoystickOverlayService.class));

        finish(); // No UI needed
    }
}
