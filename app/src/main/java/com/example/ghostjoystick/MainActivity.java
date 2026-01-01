package com.example.ghostjoystick;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(40, 40, 40, 40);

        status = new TextView(this);
        status.setTextSize(18);
        status.setPadding(0, 0, 0, 40);
        updateStatus();

        Button toggle = new Button(this);
        toggle.setText("TOGGLE INPUT");
        toggle.setOnClickListener(v -> {
            GhostIME.ENABLED = !GhostIME.ENABLED;
            updateStatus();
        });

        Button picker = new Button(this);
        picker.setText("SELECT KEYBOARD");
        picker.setOnClickListener(v -> {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showInputMethodPicker();
            }
        });

        root.addView(status,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        root.addView(toggle,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        root.addView(picker,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));

        setContentView(root);
    }

    private void updateStatus() {
        status.setText(
                GhostIME.ENABLED
                        ? "STATUS: INPUT ENABLED"
                        : "STATUS: INPUT DISABLED"
        );
    }
}
