package com.jarzasa.madridshops.activity

import android.Manifest
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
import com.jarzasa.madridshops.adapters.ActivitiesRecyclerAdapter
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.router.Router
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import com.jarzasa.madridshops.utils.addPin
import com.jarzasa.madridshops.utils.centerMapInPosition
import kotlinx.android.synthetic.main.activity_activities.*
import kotlinx.android.synthetic.main.activity_shops.*

class ActivitiesActivity : AppCompatActivity() {

    private lateinit var activitiesRecycler: RecyclerView
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        downloadActivities()

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

    //Download Activities
    private fun downloadActivities() {
        activity_list_progress_bar.visibility = View.VISIBLE
        val allActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(e: Activities) {
                        initialiceMaps(e)
                        initialiceList(e)
                        activity_list_progress_bar.visibility = View.INVISIBLE
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ActivitiesActivity)
                                .setTitle("ERROR")
                                .setMessage(errorMessage)
                                .setPositiveButton("Intentar", { dialog, witch ->
                                    dialog.dismiss()
                                    downloadActivities()
                                })
                                .setNegativeButton("Salir",  { dialog, which ->
                                    finish()
                                })
                                .show()
                    }
                }
        )
    }

    private fun initialiceList(activities: Activities) {

        activitiesRecycler = findViewById(R.id.activities_recycler_view) as RecyclerView
        activitiesRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activitiesRecycler.itemAnimator = DefaultItemAnimator()
        val adapter = ActivitiesRecyclerAdapter(activities)
        adapter.onClickListener = View.OnClickListener {
            val position = activitiesRecycler.getChildAdapterPosition(it)
            val activity = activities.get(position)
            Router().navigateFromActivitiesToActivityDetail(this, activity)
        }
        activitiesRecycler.adapter = adapter
    }

    private fun initialiceMaps(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_activities_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            centerMapInPosition(it, 40.4168, -3.7038, 13f)
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

    fun addAllPins(activities: Activities) {
        activities.forEach {
            addPin(map, it.latitude.toDouble(), it.longitude.toDouble(), it.name, it.address)
        }
    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //Como no tengo permisos, se los pido al usuario
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 10)
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
