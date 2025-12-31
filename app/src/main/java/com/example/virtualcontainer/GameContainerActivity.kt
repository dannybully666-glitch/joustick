package com.example.container

import android.app.Activity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager

class GameContainerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        val surfaceView = SurfaceView(this)

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // ✅ Container is alive
                // Rendering or attachment point goes here later
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                // Cleanup later
            }
        })

        setContentView(surfaceView)
    }
}
