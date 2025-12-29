package com.joystickapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GamepadHidService gamepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gamepad = new GamepadHidService();
        gamepad.init(this);

        JoystickView joystick = findViewById(R.id.joystick);

        joystick.setOnMoveListener((x, y) -> {
            gamepad.sendAxis(x, y);
        });
    }
}
