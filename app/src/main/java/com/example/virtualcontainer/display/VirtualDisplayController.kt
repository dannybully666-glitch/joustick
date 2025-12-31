package com.example.virtualcontainer.display

import android.content.Context
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.util.DisplayMetrics
import android.view.Surface

class VirtualDisplayController(
    context: Context,
    surface: Surface
) {
    val display: android.view.Display
    private val virtualDisplay: VirtualDisplay

    init {
        val dm = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val metrics = context.resources.displayMetrics

        virtualDisplay = dm.createVirtualDisplay(
            "GameDisplay",
            metrics.widthPixels,
            metrics.heightPixels,
            metrics.densityDpi,
            surface,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC
        )!!

        display = virtualDisplay.display
    }

    fun release() {
        virtualDisplay.release()
    }
}
