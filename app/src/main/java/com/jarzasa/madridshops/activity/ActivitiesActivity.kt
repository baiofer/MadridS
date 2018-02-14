package com.jarzasa.madridshops.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractor
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.fragment.ActivityListFragment
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

class ActivitiesActivity : AppCompatActivity() {

    var listFragment: ActivityListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        setupMap()
        setupList()

    }

    private fun setupMap() {

        val allActivitiesInteractor: GetAllActivitiesInteractor = GetAllActivitiesInteractorImpl(this)
        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(activities: Activities) {
                        initialiceMaps()
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


    private fun initialiceMaps() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_activities_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({

        })
    }
}
