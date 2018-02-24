package com.jarzasa.madridshops.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String, address: String) {
    map.addMarker(MarkerOptions()
            .position(LatLng(latitude, longitude))
            .title(title))
            .snippet = address
}

fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double, zoom: Float) {
    val coordinate = LatLng(latitude, longitude)
    val cameraPosition = CameraPosition.Builder()
            .target(coordinate)
            .zoom(zoom)
            .build()
    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
}
