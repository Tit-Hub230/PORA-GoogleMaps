package com.example.travelplannertest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.travelplannertest.databinding.ActivityAddTravelBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
//import org.bson.Document

class AddTravelActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityAddTravelBinding
    private lateinit var map: GoogleMap
    private var selectedLocation: LatLng? = null // Stores the clicked location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTravelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Set up the Add button
        /*binding.btnAdd.setOnClickListener {
            val date = binding.etDate.text.toString()
            val time = binding.etTime.text.toString()
            val reminder = binding.cbReminder.isChecked

            if (selectedLocation == null) {
                showToast("Please select a location on the map.")
                return@setOnClickListener
            }

            if (date.isEmpty() || time.isEmpty()) {
                showToast("Please fill in all fields.")
                return@setOnClickListener
            }

            // Create the document to store in MongoDB
            val travelData = Document("location", mapOf(
                "latitude" to selectedLocation!!.latitude,
                "longitude" to selectedLocation!!.longitude
            ))
                .append("date", date)
                .append("time", time)
                .append("reminder", reminder)

            // Insert the data into MongoDB
            MongoDBClient.insertTravelData(
                databaseName = "PORA",
                collectionName = "Travels",
                travelData = travelData,
                onSuccess = {
                    runOnUiThread {
                        showToast("Travel data added successfully!")
                        finish()
                    }
                },
                onFailure = { e ->
                    runOnUiThread {
                        showToast("Failed to add travel data: ${e.message}")
                    }
                }
            )
        }*/
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Enable zoom controls and gestures
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isZoomGesturesEnabled = true
        map.uiSettings.isScrollGesturesEnabled = true
        map.uiSettings.isTiltGesturesEnabled = true
        map.uiSettings.isRotateGesturesEnabled = true



        // Set a default location (e.g., San Francisco)
        val defaultLocation = LatLng(37.7749, -122.4194)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 10f))

        // Handle map clicks
        map.setOnMapClickListener { latLng ->
            selectedLocation = latLng
            map.clear() // Clear any previous markers
            map.addMarker(MarkerOptions().position(latLng).title("Selected Location"))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
