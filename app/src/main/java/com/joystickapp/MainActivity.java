package com.joystickapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JoystickView joystick = findViewById(R.id.joystick);
        TextView debugText = findViewById(R.id.debugText);

        joystick.setOnMoveListener((x, y) -> {
            StringBuilder out = new StringBuilder();

            if (y > 0.4f) out.append("W ");
            if (y < -0.4f) out.append("S ");
            if (x < -0.4f) out.append("A ");
            if (x > 0.4f) out.append("D ");

            if (out.length() == 0) {
                debugText.setText("CENTER");
            } else {
                debugText.setText(out.toString().trim());
            }
        });
    }
}
