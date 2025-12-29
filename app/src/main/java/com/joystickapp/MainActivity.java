package com.joystickapp;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView dirText;
    private String lastDir = "CENTER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dirText = findViewById(R.id.dirText);
        dirText.setText("CENTER");
    }

    // THIS is what JoystickView calls
    public void onDirection(String dir) {
        if (dir.equals(lastDir)) return; // stop spam
        lastDir = dir;

        // Update UI
        dirText.setText(dir);

        // Send keys (WASD)
        sendKeys(dir);
    }

    private void sendKeys(String dir) {
        WASDAccessibilityService svc = WASDAccessibilityService.instance;
        if (svc == null) return;

        svc.releaseAll(); // release previous keys

        switch (dir) {
            case "W": svc.press(KeyEvent.KEYCODE_W); break;
            case "A": svc.press(KeyEvent.KEYCODE_A); break;
            case "S": svc.press(KeyEvent.KEYCODE_S); break;
            case "D": svc.press(KeyEvent.KEYCODE_D); break;

            case "W+A":
                svc.press(KeyEvent.KEYCODE_W);
                svc.press(KeyEvent.KEYCODE_A);
                break;

            case "W+D":
                svc.press(KeyEvent.KEYCODE_W);
                svc.press(KeyEvent.KEYCODE_D);
                break;

            case "S+A":
                svc.press(KeyEvent.KEYCODE_S);
                svc.press(KeyEvent.KEYCODE_A);
                break;

            case "S+D":
                svc.press(KeyEvent.KEYCODE_S);
                svc.press(KeyEvent.KEYCODE_D);
                break;
        }
    }
}
