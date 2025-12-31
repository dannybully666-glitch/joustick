package com.example.virtualcontainer.input

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import kotlin.math.hypot

class JoystickView(context: Context) : View(context) {

    private val basePaint = Paint().apply {
        color = Color.argb(120, 0, 0, 0)
    }

    private val knobPaint = Paint().apply {
        color = Color.argb(180, 255, 255, 255)
    }

    private var centerX = 300f
    private var centerY = 800f
    private var knobX = centerX
    private var knobY = centerY
    private val radius = 200f

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(centerX, centerY, radius, basePaint)
        canvas.drawCircle(knobX, knobY, 60f, knobPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val dx = event.x - centerX
        val dy = event.y - centerY
        val dist = hypot(dx, dy)

        if (dist > radius) {
            val scale = radius / dist
            knobX = centerX + dx * scale
            knobY = centerY + dy * scale
        } else {
            knobX = event.x
            knobY = event.y
        }

        invalidate()
        return true
    }
}
