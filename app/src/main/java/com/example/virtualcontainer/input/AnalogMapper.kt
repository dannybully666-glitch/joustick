package com.example.virtualcontainer.input

object AnalogMapper {
    fun toDirection(x: Float, y: Float): String {
        return when {
            y < -0.5 -> "UP"
            y > 0.5 -> "DOWN"
            x < -0.5 -> "LEFT"
            x > 0.5 -> "RIGHT"
            else -> "CENTER"
        }
    }
}
