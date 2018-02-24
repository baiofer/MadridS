package com.jarzasa.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.router.Router
import com.jarzasa.madridshops.utils.INTENT_SHOP_DETAIL
import com.jarzasa.madridshops.utils.addPin
import com.jarzasa.madridshops.utils.centerMapInPosition
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        //Recoger la tienda
        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        shop_detail_text_view.text = shop.description
        Picasso.with(this)
                .load(shop.image)
                .placeholder(android.R.drawable.btn_radio)
                .into(shop_detail_image)

        initialiceMaps(shop)


        //Activo el botón de back de la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = shop.name
    }

    //Si pulsan el botón Back, salgo de la actividad sin mas y vuelvo a la anterior
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialiceMaps(shop: Shop) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.shop_detail_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            //centerMapInPosition(map, 42.9017754, -2.3397827) // Zalduondo
            centerMapInPosition(it, shop.latitude.toDouble(), shop.longitude.toDouble(), 15f)
            //Configuramos los settings del mapa
            it.uiSettings.isRotateGesturesEnabled = false  //No dejo girar el mapa con dos dedos)
            it.uiSettings.isZoomControlsEnabled = true     //Aparecen los controles de zoom
            map = it
            addPin(map, shop.latitude.toDouble(), shop.longitude.toDouble(), shop.name, shop.address)
        })
    }
}