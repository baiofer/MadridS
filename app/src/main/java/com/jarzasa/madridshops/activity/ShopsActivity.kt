package com.jarzasa.madridshops.activity

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.SupportMapFragment
import com.jarzasa.madridshops.R
import com.jarzasa.madridshops.domain.interactors.getallshops.GetAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.fragment.ListFragment
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

class ShopsActivity : AppCompatActivity() {

    var listFragment: ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)

        setupMap()
        setupList()

    }

    private fun setupMap() {

        val allShopsInteractor = GetAllShopsInteractorImpl(this)
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        initialiceMaps()
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ShopsActivity)
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

        listFragment = supportFragmentManager.findFragmentById(R.id.activity_shops_list_fragment) as ListFragment
        val listFragmentInmutable = listFragment

        val allShopsInteractor = GetAllShopsInteractorImpl(this)
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(shops: Shops) {
                        listFragmentInmutable?.setItems(shops)
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        AlertDialog.Builder(this@ShopsActivity)
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
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_shops_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({

        })
    }
}
