package com.jarzasa.madridshops

import android.support.multidex.MultiDexApplication
import com.jarzasa.madridshops.router.Router
import com.squareup.picasso.Picasso

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        //Initialize Picasso
        Picasso.with(this).setIndicatorsEnabled(true)
        Picasso.with(this).isLoggingEnabled()

        //Begins app
        Router().navigateFromMadridShopsAppToEntrance(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
