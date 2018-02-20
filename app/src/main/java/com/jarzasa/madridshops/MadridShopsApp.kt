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

        //Starts the application
        Router().navigateFromMadridShopsAppToEntrance(this)
        //val intent: Intent = EntranceActivity.intent(this)
        //val intent: Intent = PicassoActivity.intent(this)
        //startActivity(intent)

    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
