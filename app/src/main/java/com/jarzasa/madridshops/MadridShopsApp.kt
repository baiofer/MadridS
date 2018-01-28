package com.jarzasa.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.jarzasa.madridshops.domain.interactors.ErrorCompletion
import com.jarzasa.madridshops.domain.interactors.GetAllShopsInteractorFakeImpl
import com.jarzasa.madridshops.domain.interactors.SuccessCompletion
import com.jarzasa.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(e: Shops) {
                        Log.d("Shops", "Count: " + e.count())
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Shops", "Error: " + errorMessage)
                    }
                }
        )
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
