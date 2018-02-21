package com.jarzasa.madridshops.activity

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractor
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.fragment.ActivityListFragment
import com.jarzasa.madridshops.router.Router
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

class ActivitiesActivity : AppCompatActivity() {

    private var listFragment: ActivityListFragment? = null
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        setupMap()
        setupList()

        //Activo el botón de back de la barra
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    //Si pulsan el botón Back, salgo de la actividad sin mas y vuelvo a la anterior
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupMap() {

        val allActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(activities: Activities) {
                        initialiceMaps(activities)
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ActivitiesActivity)
                                .setTitle("ERROR")
                                .setMessage(errorMessage)
                                .setPositiveButton("Intentar", { dialog, witch ->
                                    dialog.dismiss()
                                    setupMap()
                                })
                                .setNegativeButton("Salir",  { dialog, which ->
                                    finish()
                                })
                                .show()
                    }
                }
        )
    }

    private fun setupList() {

        listFragment = supportFragmentManager.findFragmentById(R.id.activity_activities_list_fragment) as ActivityListFragment
        val listFragmentInmutable = listFragment

        val allActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(activities: Activities) {
                        listFragmentInmutable?.setItems(activities)
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ActivitiesActivity)
                                .setTitle("ERROR")
                                .setMessage(errorMessage)
                                .setPositiveButton("Intentar", { dialog, witch ->
                                    dialog.dismiss()
                                    setupList()
                                })
                                .setNegativeButton("Salir",  { dialog, which ->
                                    finish()
                                })
                                .show()
                    }
                }
        )
    }

    private fun initialiceMaps(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_activities_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            //centerMapInPosition(map, 42.9017754, -2.3397827) // Zalduondo
            centerMapInPosition(it, 40.4168, -3.7038 )
            //Configuramos los settings del mapa
            it.uiSettings.isRotateGesturesEnabled = false  //No dejo girar el mapa con dos dedos)
            it.uiSettings.isZoomControlsEnabled = true     //Aparecen los controles de zoom
            //Indico mi posición
            showUserPosition(baseContext, it)
            //Actuo sobre un título de pin seleccionado
            it.setOnInfoWindowClickListener {
                val activity = activities.get(it.title)
                Log.d("Shop", activity?.name)
                if (activity != null) {
                    Router().navigateFromActivitiesToActivityDetail(this, activity)
                }
            }
            map = it
            addAllPins(activities)
        })
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(13f)
                .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun addAllPins(activities: Activities) {
        activities.forEach {
            addPin(map, it.latitude.toDouble(), it.longitude.toDouble(), it.name)
        }
    }

    fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String) {
        map.addMarker(MarkerOptions()
                .position(LatLng(latitude, longitude))
                .title(title))
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Como no tengo permisos, se los pido al usuario
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 10)
            //Alert al usuario. Necesitamos su localización para acceder a las tiendas
            return
        }

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
