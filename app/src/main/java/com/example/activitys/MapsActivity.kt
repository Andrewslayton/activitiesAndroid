package com.example.activitys

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions


class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var distanceSeekBar: SeekBar
    private lateinit var distanceTextView: TextView
    private var currentLocation: Location? = null
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        setupSeekBar()
        getCurrentLocation()
        setupNextButton()
        userId = FirebaseAuth.getInstance().currentUser?.uid
    }

    private fun setupSeekBar() {
        distanceSeekBar = findViewById(R.id.distanceSeekBar)
        distanceTextView = findViewById(R.id.distanceTextView)

        distanceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val distance = progress + 1
                distanceTextView.text = "Distance: $distance miles"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun setupNextButton() {
        val nextButton: Button = findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            if (currentLocation != null && userId != null) {
                val latLng = LatLng(currentLocation!!.latitude, currentLocation!!.longitude)
                val distance = distanceSeekBar.progress + 1
                saveUserLocationAndDistance(userId!!, latLng, distance)
//                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                currentLocation = location
                val userLatLng = LatLng(location.latitude, location.longitude)
                println(userLatLng)
                mMap.addMarker(MarkerOptions().position(userLatLng).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 12f))
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        getCurrentLocation()
    }

    private fun saveUserLocationAndDistance(userId: String, latLng: LatLng, distance: Int) {
        val db = FirebaseFirestore.getInstance()
        val locationData = hashMapOf(
            "latitude" to latLng.latitude,
            "longitude" to latLng.longitude,
            "distance" to distance
        )
        db.collection("users").document(userId).update(locationData as Map<String, Any>)
            .addOnSuccessListener {  }
            .addOnFailureListener { e ->  }
    }
}
