package com.joystickapp;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called from JoystickView
    public void onDirection(String dir) {
        WASDAccessibilityService s = WASDAccessibilityService.instance;
        if (s == null) return;

        switch (dir) {
            case "UP":
                s.sendKey(KeyEvent.KEYCODE_W);
                break;
            case "DOWN":
                s.sendKey(KeyEvent.KEYCODE_S);
                break;
            case "LEFT":
                s.sendKey(KeyEvent.KEYCODE_A);
                break;
            case "RIGHT":
                s.sendKey(KeyEvent.KEYCODE_D);
                break;
        }
    }
}
