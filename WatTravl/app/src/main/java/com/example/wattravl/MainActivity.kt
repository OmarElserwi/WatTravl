package com.example.wattravl

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add Location Spinner
        val fromLocationSpinner: Spinner = findViewById(R.id.fromLocationSpinner)
        val toLocationSpinner: Spinner = findViewById(R.id.toLocationSpinner)
        val locationItems = resources.getStringArray(R.array.Locations)
        val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationItems)
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromLocationSpinner.adapter = locationAdapter
        toLocationSpinner.adapter = locationAdapter

        // Add Room Spinner
        val fromRoomSpinner: Spinner = findViewById(R.id.fromRoomSpinner)
        val toRoomSpinner: Spinner = findViewById(R.id.toRoomSpinner)
        val roomItems = resources.getStringArray(R.array.Rooms)
        val roomAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roomItems)
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        fromRoomSpinner.adapter = roomAdapter
        toRoomSpinner.adapter = roomAdapter
    }
}
