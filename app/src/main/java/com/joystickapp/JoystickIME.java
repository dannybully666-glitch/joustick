package com.joystickapp;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;

public class JoystickIME extends InputMethodService {

    @Override
    public View onCreateInputView() {
        // NO keyboard UI
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // Volume Up toggles joystick
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            JoystickOverlayService.toggle();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
