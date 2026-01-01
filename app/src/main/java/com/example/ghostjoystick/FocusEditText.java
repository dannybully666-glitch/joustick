package com.example.ghostjoystick;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class FocusEditText extends EditText {

    public FocusEditText(Context context) {
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setCursorVisible(false);
        setBackground(null);
    }

    public void requestIme() {
        requestFocus();

        InputMethodManager imm =
                (InputMethodManager) getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }
}
