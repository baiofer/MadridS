package com.jarzasa.madridshops

import android.content.Intent
import android.support.multidex.MultiDexApplication
import com.jarzasa.madridshops.activity.EntranceActivity

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val intent: Intent = EntranceActivity.intent(this)
        startActivity(intent)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
