package com.example.virtualcontainer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Simple UI created in code (no XML needed)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 80, 40, 40)
        }

        val overlayPermissionButton = Button(this).apply {
            text = "Grant Overlay Permission"
            setOnClickListener {
                if (!Settings.canDrawOverlays(this@MainActivity)) {
                    val intent = Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                    startActivity(intent)
                }
            }
        }

        val startOverlayButton = Button(this).apply {
            text = "Start Overlay"
            setOnClickListener {
                val intent = Intent(this@MainActivity, OverlayService::class.java)
                startService(intent)
            }
        }

        layout.addView(overlayPermissionButton)
        layout.addView(startOverlayButton)

        setContentView(layout)
    }
}
