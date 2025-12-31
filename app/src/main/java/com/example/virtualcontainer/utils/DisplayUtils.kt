package com.example.virtualcontainer.utils

import android.content.Context

fun dp(context: Context, v: Float) =
    v * context.resources.displayMetrics.density
