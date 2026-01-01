package com.example.ghostjoystick;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No UI needed.
        // This activity only exists so the app can be launched
        // and the IME can be enabled in system settings.
        finish();
    }
}
