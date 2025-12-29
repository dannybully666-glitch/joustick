package com.joystickapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // ===== CALLED FROM YOUR JOYSTICK VIEW =====

    public void moveUp() {
        if (WASDAccessibilityService.instance != null)
            WASDAccessibilityService.instance.pressW();
    }

    public void moveDown() {
        if (WASDAccessibilityService.instance != null)
            WASDAccessibilityService.instance.pressS();
    }

    public void moveLeft() {
        if (WASDAccessibilityService.instance != null)
            WASDAccessibilityService.instance.pressA();
    }

    public void moveRight() {
        if (WASDAccessibilityService.instance != null)
            WASDAccessibilityService.instance.pressD();
    }
}
