package com.example.ghostjoystick;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(this, ToggleNotificationService.class);

        if (Build.VERSION.SDK_INT >= 26) {
    startForegroundService(new Intent(this, ToggleNotificationService.class));
} else {
    startService(new Intent(this, ToggleNotificationService.class));
}
finish();
    }
}
