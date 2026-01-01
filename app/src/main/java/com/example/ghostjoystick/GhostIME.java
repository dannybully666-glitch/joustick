package com.example.ghostjoystick;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;

public class GhostIME extends InputMethodService {

    public static GhostIME INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static void sendKey(int key) {
        if (INSTANCE == null) return;

        INSTANCE.sendDownUpKeyEvents(key);
    }
}
