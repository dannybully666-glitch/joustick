package com.joystickapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // ===== CALLED BY JoystickView =====
    public void onDirection(String dir) {
        if (WASDAccessibilityService.instance == null) return;

        switch (dir) {
            case "UP":
                WASDAccessibilityService.instance.pressW();
                break;
            case "DOWN":
                WASDAccessibilityService.instance.pressS();
                break;
            case "LEFT":
                WASDAccessibilityService.instance.pressA();
                break;
            case "RIGHT":
                WASDAccessibilityService.instance.pressD();
                break;
        }
    }
}
