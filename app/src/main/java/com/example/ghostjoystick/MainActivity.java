
package com.example.ghostjoystick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1️⃣ Check overlay permission
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
            );
            startActivity(intent);
            return;
        }

        // 2️⃣ Start floating toggle service
        startService(new Intent(this, ToggleOverlayService.class));

        // 3️⃣ Close activity (no UI needed)
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // User may return from overlay permission screen
        if (Settings.canDrawOverlays(this)) {
            startService(new Intent(this, ToggleOverlayService.class));
            finish();
        }
    }
}
