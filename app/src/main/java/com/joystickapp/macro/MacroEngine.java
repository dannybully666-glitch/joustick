package com.joystickapp.macro;

import android.os.Handler;

import com.joystickapp.system.InputAccessibilityService;

public class MacroEngine {

    Handler h = new Handler();

    public void run(int[] keys, int delayMs) {
        for (int i = 0; i < keys.length; i++) {
            int k = keys[i];
            h.postDelayed(() ->
                InputAccessibilityService.instance.sendKey(k),
                i * delayMs
            );
        }
    }
}
