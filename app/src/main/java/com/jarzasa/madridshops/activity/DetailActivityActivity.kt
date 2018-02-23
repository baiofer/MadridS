package com.jarzasa.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.utils.INTENT_ACTIVITY_DETAIL
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_activity.*

class DetailActivityActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_activity)

        //Recoger la tienda
        val activity = intent.getSerializableExtra(INTENT_ACTIVITY_DETAIL) as Activity

        activity_detail_text_view.text = activity.description
        Picasso.with(this)
                .load(activity.image)
                .placeholder(android.R.drawable.btn_radio)
                .into(activity_detail_image)

        initialiceMaps(activity)


        //Activo el botón de back de la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = activity.name
    }

    //Si pulsan el botón Back, salgo de la actividad sin mas y vuelvo a la anterior
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialiceMaps(activity: Activity) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_detail_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            //centerMapInPosition(map, 42.9017754, -2.3397827) // Zalduondo
            centerMapInPosition(it, activity.latitude.toDouble(), activity.longitude.toDouble() )
            //Configuramos los settings del mapa
            it.uiSettings.isRotateGesturesEnabled = false  //No dejo girar el mapa con dos dedos)
            it.uiSettings.isZoomControlsEnabled = true     //Aparecen los controles de zoom
            map = it
            addPin(map, activity.latitude.toDouble(), activity.longitude.toDouble(), activity.name, activity.address)
        })
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(15f)
                .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String, address: String?) {
        map.addMarker(MarkerOptions()
                .position(LatLng(latitude, longitude))
                .title(title))
                .snippet = address

    }
}
