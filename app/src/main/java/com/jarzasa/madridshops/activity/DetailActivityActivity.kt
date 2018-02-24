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
import com.jarzasa.madridshops.utils.addPin
import com.jarzasa.madridshops.utils.centerMapInPosition
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
                .placeholder(R.drawable.madrid)
                .into(activity_detail_image)

        initialiceMap(activity)

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

    private fun initialiceMap(activity: Activity) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_detail_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            centerMapInPosition(it, activity.latitude.toDouble(), activity.longitude.toDouble(), 15f)
            //Configuramos los settings del mapa
            it.uiSettings.isRotateGesturesEnabled = false  //No dejo girar el mapa con dos dedos)
            it.uiSettings.isZoomControlsEnabled = true     //Aparecen los controles de zoom
            map = it
            addPin(map, activity.latitude.toDouble(), activity.longitude.toDouble(), activity.name, activity.address)
        })
    }
}
