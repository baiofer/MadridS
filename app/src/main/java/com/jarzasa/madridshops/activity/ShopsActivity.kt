package com.jarzasa.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.adapters.ShopsRecyclerAdapter
import com.jarzasa.madridshops.domain.interactors.getallshops.GetAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.router.Router
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import com.jarzasa.madridshops.utils.addPin
import com.jarzasa.madridshops.utils.centerMapInPosition
import kotlinx.android.synthetic.main.activity_shops.*

class ShopsActivity : AppCompatActivity() {

    private lateinit var shopsRecycler: RecyclerView
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)

        downloadShops()

        //Activo el botón de back de la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Download Shops
    private fun downloadShops() {
        shop_list_progress_bar.visibility = View.VISIBLE
        val allShopsInteractor = GetAllShopsInteractorImpl(this)
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(e: Shops) {
                        initialiceMaps(e)
                        initialiceList(e)
                        shop_list_progress_bar.visibility = View.INVISIBLE
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ShopsActivity)
                                .setTitle("ERROR")
                                .setMessage(errorMessage)
                                .setPositiveButton("Intentar", { dialog, witch ->
                                    dialog.dismiss()
                                    downloadShops()
                                })
                                .setNegativeButton("Salir",  { dialog, which ->
                                    finish()
                                })
                                .show()
                    }
                }
        )
    }

    //Si pulsan el botón Back, salgo de la actividad sin mas y vuelvo a la anterior
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initialiceList(shops: Shops) {

        shopsRecycler = findViewById(R.id.shops_recycler_view) as RecyclerView
        shopsRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        shopsRecycler.itemAnimator = DefaultItemAnimator()
        val adapter = ShopsRecyclerAdapter(shops)
        adapter.onClickListener = View.OnClickListener {
            val position = shopsRecycler.getChildAdapterPosition(it)
            val shop = shops.get(position)
            Router().navigateFromShopsToShopDetail(this, shop)
        }
        shopsRecycler.adapter = adapter
    }

    private fun initialiceMaps(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_shops_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            centerMapInPosition(it, 40.4168, -3.7038, 13f)
            //Configuramos los settings del mapa
            it.uiSettings.isRotateGesturesEnabled = false  //No dejo girar el mapa con dos dedos)
            it.uiSettings.isZoomControlsEnabled = true     //Aparecen los controles de zoom
            //Indico mi posición
            showUserPosition(baseContext, it)
            //Actuo sobre un título de pin seleccionado
            it.setOnInfoWindowClickListener {
                val shop = shops.get(it.title)
                Log.d("Shop", shop?.name)
                if (shop != null) {
                    Router().navigateFromShopsToShopDetail(this, shop)
                }
            }
            map = it
            addAllPins(shops)
        })
    }

    fun addAllPins(shops: Shops) {
        shops.forEach {
            addPin(map, it.latitude.toDouble(), it.longitude.toDouble(), it.name, it.address)
        }
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Como no tengo permisos, se los pido al usuario
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 10)
            return
        }
        map.isMyLocationEnabled = true
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 10) {
            try {
                map.isMyLocationEnabled = true
            }
            catch (e: SecurityException) {

            }
        }
    }
}
