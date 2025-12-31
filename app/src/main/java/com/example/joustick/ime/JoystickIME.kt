package com.example.joustick.ime

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import com.example.joustick.R

class JoystickIME : InputMethodService() {

    override fun onCreateInputView(): View {
        val view = layoutInflater.inflate(R.layout.ime_view, null)

        bindKey(view, R.id.btn_w, KeyEvent.KEYCODE_W)
        bindKey(view, R.id.btn_a, KeyEvent.KEYCODE_A)
        bindKey(view, R.id.btn_s, KeyEvent.KEYCODE_S)
        bindKey(view, R.id.btn_d, KeyEvent.KEYCODE_D)

        return view
    }

    private fun bindKey(view: View, buttonId: Int, keyCode: Int) {
        val btn = view.findViewById<Button>(buttonId)

        btn.setOnTouchListener { _, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    sendKey(keyCode, true)
                }
                android.view.MotionEvent.ACTION_UP,
                android.view.MotionEvent.ACTION_CANCEL -> {
                    sendKey(keyCode, false)
                }
            }
            true
        }
    }

    private fun sendKey(keyCode: Int, down: Boolean) {
        val eventTime = System.currentTimeMillis()
        val action = if (down) KeyEvent.ACTION_DOWN else KeyEvent.ACTION_UP

        currentInputConnection.sendKeyEvent(
            KeyEvent(eventTime, eventTime, action, keyCode, 0)
        )
    }
}
