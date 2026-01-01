// MainActivity.java
package com.example.ghostjoystick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start toggle notification
        startService(new Intent(this, ToggleNotificationService.class));

        // Close UI immediately (like GameKeyboard)
        finish();
    }
}
