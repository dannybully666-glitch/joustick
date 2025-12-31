package com.example.virtualcontainer.input

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import kotlin.math.hypot

class JoystickView(context: Context) : View(context) {

    var onMove: ((Float, Float) -> Unit)? = null

    private val basePaint = Paint().apply {
        color = 0x55FFFFFF
        isAntiAlias = true
    }

    private val knobPaint = Paint().apply {
        color = 0xAAFFFFFF
        isAntiAlias = true
    }

    private var cx = 0f
    private var cy = 0f
    private var kx = 0f
    private var ky = 0f
    private var radius = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cx = w / 2f
        cy = h / 2f
        kx = cx
        ky = cy
        radius = minOf(w, h) / 2.5f
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, basePaint)
        canvas.drawCircle(kx, ky, radius / 2, knobPaint)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        val dx = e.x - cx
        val dy = e.y - cy
        val dist = hypot(dx, dy)

        if (e.action != MotionEvent.ACTION_UP) {
            val scale = if (dist > radius) radius / dist else 1f
            kx = cx + dx * scale
            ky = cy + dy * scale
            onMove?.invoke(dx / radius, dy / radius)
        } else {
            kx = cx
            ky = cy
            onMove?.invoke(0f, 0f)
        }

        invalidate()
        return true
    }
}
