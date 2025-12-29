package com.joystickapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = findViewById(R.id.statusText);
    }

    // Called by JoystickView
    public void onDirection(String dir) {
        status.setText(dir);

        WASDAccessibilityService svc = WASDAccessibilityService.instance;
        if (svc == null) return;

        // Release previous keys first
        svc.releaseAll();

        switch (dir) {
            case "W":
                svc.pressW();
                break;
            case "A":
                svc.pressA();
                break;
            case "S":
                svc.pressS();
                break;
            case "D":
                svc.pressD();
                break;
            case "WA":
                svc.pressW();
                svc.pressA();
                break;
            case "WD":
                svc.pressW();
                svc.pressD();
                break;
            case "SA":
                svc.pressS();
                svc.pressA();
                break;
            case "SD":
                svc.pressS();
                svc.pressD();
                break;
        }
    }

    // Called when joystick is released (VERY IMPORTANT)
    public void onRelease() {
        WASDAccessibilityService svc = WASDAccessibilityService.instance;
        if (svc != null) {
            svc.releaseAll();
        }
        status.setText("CENTER");
    }
}
