package com.example.joustick.ime

import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import com.example.joustick.R

class JoystickIME : InputMethodService() {

    override fun onCreateInputView(): View {
        val v = layoutInflater.inflate(R.layout.ime_view, null)

        bind(v, R.id.w, KeyEvent.KEYCODE_W)
        bind(v, R.id.a, KeyEvent.KEYCODE_A)
        bind(v, R.id.s, KeyEvent.KEYCODE_S)
        bind(v, R.id.d, KeyEvent.KEYCODE_D)

        return v
    }

    private fun bind(root: View, id: Int, key: Int) {
        root.findViewById<Button>(id).setOnTouchListener { _, e ->
            when (e.action) {
                MotionEvent.ACTION_DOWN -> send(key, true)
                MotionEvent.ACTION_UP,
                MotionEvent.ACTION_CANCEL -> send(key, false)
            }
            true
        }
    }

    private fun send(key: Int, down: Boolean) {
        val t = System.currentTimeMillis()
        currentInputConnection.sendKeyEvent(
            KeyEvent(t, t,
                if (down) KeyEvent.ACTION_DOWN else KeyEvent.ACTION_UP,
                key, 0)
        )
    }
}
