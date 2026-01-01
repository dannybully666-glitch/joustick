package com.example.ghostjoystick;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class GhostEditText extends EditText {

    public GhostEditText(Context c) {
        super(c);

        setWidth(1);
        setHeight(1);
        setAlpha(0f);
        setCursorVisible(false);
        setBackground(null);
        setFocusable(true);
        setFocusableInTouchMode(true);

        post(() -> {
            requestFocus();
            InputMethodManager imm =
                    (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
        });
    }
}
