package com.example.wattravl

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the parameters from the Intent
        val selectedFromLocation = intent.getStringExtra("SELECTED_FROM_LOCATION")
        val selectedToLocation = intent.getStringExtra("SELECTED_TO_LOCATION")
        val selectedFromRoom = intent.getStringExtra("SELECTED_FROM_ROOM")
        val selectedToRoom = intent.getStringExtra("SELECTED_TO_ROOM")

        val btn: Button = findViewById(R.id.button2)
        btn.text = "PUT MAP HERE:\n" + "From Location: $selectedFromLocation Room $selectedFromRoom\nTo Location: $selectedToLocation Room $selectedToRoom"
    }
}