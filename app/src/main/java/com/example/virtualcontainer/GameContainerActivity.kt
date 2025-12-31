package com.example.virtualcontainer

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.SurfaceView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.virtualcontainer.display.VirtualDisplayController
import com.example.virtualcontainer.input.JoystickView

class GameContainerActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView
    private lateinit var virtualDisplayController: VirtualDisplayController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = FrameLayout(this)
        surfaceView = SurfaceView(this)

        val joystick = JoystickView(this)

        root.addView(surfaceView)
        root.addView(joystick)

        setContentView(root)

        surfaceView.holder.addCallback(object : android.view.SurfaceHolder.Callback {
            override fun surfaceCreated(holder: android.view.SurfaceHolder) {
                startVirtualDisplay(holder.surface)
            }
            override fun surfaceChanged(h: android.view.SurfaceHolder, f: Int, w: Int, h2: Int) {}
            override fun surfaceDestroyed(h: android.view.SurfaceHolder) {}
        })
    }

    private fun startVirtualDisplay(surface: android.view.Surface) {
        virtualDisplayController =
            VirtualDisplayController(this, surface)

        val pkg = intent.getStringExtra("TARGET_PACKAGE") ?: return
        launchAppOnDisplay(pkg)
    }

    private fun launchAppOnDisplay(pkg: String) {
        val intent = packageManager.getLaunchIntentForPackage(pkg) ?: return
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val options = ActivityOptions.makeBasic()
        options.launchDisplayId = virtualDisplayController.display.displayId

        startActivity(intent, options.toBundle())
    }
}
