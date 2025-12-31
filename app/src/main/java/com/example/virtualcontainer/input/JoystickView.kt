package com.example.virtualcontainer.input

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.math.sqrt

class JoystickView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val basePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.argb(120, 0, 0, 0)
        style = Paint.Style.FILL
    }

    private val knobPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.argb(200, 255, 255, 255)
        style = Paint.Style.FILL
    }

    private var centerX = 0f
    private var centerY = 0f
    private var baseRadius = 0f
    private var knobRadius = 0f

    private var knobX = 0f
    private var knobY = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = w / 2f
        centerY = h / 2f

        baseRadius = min(w, h) * 0.4f
        knobRadius = baseRadius * 0.4f

        knobX = centerX
        knobY = centerY
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(centerX, centerY, baseRadius, basePaint)
        canvas.drawCircle(knobX, knobY, knobRadius, knobPaint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val dx = event.x - centerX
        val dy = event.y - centerY
        val distance = sqrt(dx * dx + dy * dy)

        when (event.action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                if (distance <= baseRadius) {
                    knobX = event.x
                    knobY = event.y
                } else {
                    val angle = atan2(dy, dx)
                    knobX = centerX + cos(angle) * baseRadius
                    knobY = centerY + sin(angle) * baseRadius
                }
                invalidate()
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                knobX = centerX
                knobY = centerY
                invalidate()
            }
        }
        return true
    }
}
