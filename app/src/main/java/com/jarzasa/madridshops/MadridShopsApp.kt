package com.jarzasa.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.jarzasa.madridshops.domain.interactors.deleteallactivities.DeleteAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.interactors.deleteallshops.DeleteAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.interactors.getallshops.GetAllShopsInteractorFakeImpl
import com.jarzasa.madridshops.domain.model.Shops

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()

        /*
        //Invocación del modo Java
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
        */
        //Invocación del modo Kotlin
        allShopsInteractor.execute(
                success = { shops: Shops ->
                    Log.d("Shops", "Count: " + shops.count())
                },
                error = { error: String ->
                    Log.d("Shops", "Error: " + error)
                }
        )

        val server = BuildConfig.MS_SERVER_URL
        Log.d("APP", server)

        DeleteAllShopsInteractorImpl(this).execute(success = {
            Log.d("SUCCESS", "ALL SHOPS DELETED")
        }, error = {
            Log.d("ERROR", "ERROR DELETING SHOPS")
        })

        DeleteAllActivitiesInteractorImpl(this).execute(success = {
            Log.d("SUCCESS", "ALL ACTIVITIES DELETED")
        }, error = {
            Log.d("ERROR", "ERROR DELETING ACTIVITIES")
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
