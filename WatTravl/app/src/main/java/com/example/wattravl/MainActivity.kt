package com.example.wattravl

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.wattravl.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest

class MainActivity : AppCompatActivity() {

    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
    private val fastestInterval: Long = 5000 // 5 seconds
    private lateinit var locationRequest: LocationRequest
    private val requestPermissionCode = 999
    private val interval: Long = 10000 // 10 seconds
    private lateinit var binding: ActivityMainBinding
    private lateinit var longitudeText: TextView
    private lateinit var latitudeText: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.create()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            showAlertMessage()
        }

        longitudeText = findViewById(R.id.longitudeText)
        latitudeText = findViewById(R.id.latitudeText)

        checkForPermission(this)
        startLocationUpdates()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Add Location Spinner
        val fromLocationSpinner: Spinner = findViewById(R.id.fromLocationSpinner)
        val toLocationSpinner: Spinner = findViewById(R.id.toLocationSpinner)
        val locationItems = resources.getStringArray(R.array.Locations)
        val locationAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, locationItems)
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

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            // Get the selected items from the spinners
            val selectedFromLocation = fromLocationSpinner.selectedItem.toString()
            val selectedToLocation = toLocationSpinner.selectedItem.toString()
            val selectedFromRoom = fromRoomSpinner.selectedItem.toString()
            val selectedToRoom = toRoomSpinner.selectedItem.toString()

            // Start SecondActivity and pass the selected items
            val intent = Intent(this, MapActivity::class.java).apply {
                putExtra("SELECTED_FROM_LOCATION", selectedFromLocation)
                putExtra("SELECTED_TO_LOCATION", selectedToLocation)
                putExtra("SELECTED_FROM_ROOM", selectedFromRoom)
                putExtra("SELECTED_TO_ROOM", selectedToRoom)
            }
            startActivity(intent)
        }

        val locationRefreshButton: Button = findViewById(R.id.refreshButton)
        locationRefreshButton.setOnClickListener {
            Log.d("MainActivity", "Refreshing location")
            updateLocation()
            startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        fusedLocationClient?.removeLocationUpdates(locationCallback)
        Log.d("MainActivity", "onPause: Removing location updates")
        longitudeText.text = "Longitude: N/A"
        latitudeText.text = "Latitude: N/A"
    }

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Log.d("MainActivity", "mLocationCallback: updating location")
            updateUI(locationResult.lastLocation)
        }
    }

    private fun updateUI(lastLocation: Location?) {
        latitude = lastLocation?.latitude ?: 0.0
        longitude = lastLocation?.longitude ?: 0.0
        Log.d("MainActivity", "updateUI: $latitude, $longitude")
        longitudeText.text = "Longitude: $longitude"
        latitudeText.text = "Latitude: $latitude"
    }

    private fun startLocationUpdates() {
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = interval
        locationRequest.fastestInterval = fastestInterval

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(locationRequest)
        val locationSettingsRequest = builder.build()
        val settingsClient = LocationServices.getSettingsClient(this)
        settingsClient.checkLocationSettings(locationSettingsRequest)

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        fusedLocationClient!!.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null)
    }

    private fun updateLocation() {
        Log.d("MainActivity", "updateLocation: called")
        checkForPermission(this)
        fusedLocationClient?.lastLocation?.addOnSuccessListener { updateUI(it) }
    }

    private fun checkForPermission(context: Context) {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            return
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                requestPermissionCode)
            return
        }
    }

    private fun showAlertMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("The location permission is disabled. Do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 10)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
                finish()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestPermissionCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
