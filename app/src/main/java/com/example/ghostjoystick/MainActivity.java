package com.example.ghostjoystick;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start notification toggle service
        startForegroundService(
                new Intent(this, ToggleNotificationService.class)
        );

        finish(); // No UI
    }
}
