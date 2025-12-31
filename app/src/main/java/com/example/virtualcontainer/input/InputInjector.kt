package com.example.virtualcontainer.input

import android.util.Log

object InputInjector {
    fun inject(x: Float, y: Float) {
        Log.d("Injector", "Analog x=$x y=$y")
    }
}
