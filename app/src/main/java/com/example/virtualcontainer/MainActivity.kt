package com.example.virtualcontainer

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TEMP: launch directly
        val intent = Intent(this, GameContainerActivity::class.java)
        intent.putExtra("TARGET_PACKAGE", "com.example.targetgame")
        startActivity(intent)
        finish()
    }
}
