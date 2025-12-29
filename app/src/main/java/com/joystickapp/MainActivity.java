package com.joystickapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean w, a, s, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JoystickView joystick = findViewById(R.id.joystick);

        joystick.setOnMoveListener((angle, strength) -> {
            if (strength < 0.2f) {
                releaseAll();
                return;
            }

            boolean nw = false, na = false, ns = false, nd = false;

            if (angle >= 337.5 || angle < 22.5) nd = true;
            else if (angle < 67.5) { nd = true; nw = true; }
            else if (angle < 112.5) nw = true;
            else if (angle < 157.5) { nw = true; na = true; }
            else if (angle < 202.5) na = true;
            else if (angle < 247.5) { na = true; ns = true; }
            else if (angle < 292.5) ns = true;
            else { ns = true; nd = true; }

            updateKey("W", w, nw); w = nw;
            updateKey("A", a, na); a = na;
            updateKey("S", s, ns); s = ns;
            updateKey("D", d, nd); d = nd;
        });
    }

    private void updateKey(String key, boolean oldState, boolean newState) {
        if (oldState != newState) {
            if (newState) {
                Log.d("WASD", key + " DOWN");
            } else {
                Log.d("WASD", key + " UP");
            }
        }
    }

    private void releaseAll() {
        updateKey("W", w, false);
        updateKey("A", a, false);
        updateKey("S", s, false);
        updateKey("D", d, false);
        w = a = s = d = false;
    }
}
