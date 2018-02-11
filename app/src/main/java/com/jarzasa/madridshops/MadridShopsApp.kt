package com.jarzasa.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.jarzasa.madridshops.domain.interactors.getallactivities.GetAllActivitiesInteractorImpl
import com.jarzasa.madridshops.domain.interactors.getallshops.GetAllShopsInteractorImpl
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

class MadridShopsApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        //Invocación del modo Java
        allShopsInteractor.execute(
                success = object: SuccessCompletion<Shops> {
                    override fun successCompletion(e: Shops) {
                        Log.d("Shops", "Count: " + e.count())
                        e.shops.forEach {
                            Log.d("Shop",it.name + "  " + it.latitude + "   " + it.longitude)
                        }
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Shops", "Error: " + errorMessage)
                    }
                }
        )

        val allActivitiesInteractor = GetAllActivitiesInteractorImpl(this)

        //Invocación del modo Java
        allActivitiesInteractor.execute(
                success = object: SuccessCompletion<Activities> {
                    override fun successCompletion(e: Activities) {
                        Log.d("Activities", "Count: " + e.count())
                        e.activities.forEach {
                            Log.d("Activity", it.name)
                        }
                    }
                },
                error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("Activities", "Error: " + errorMessage)
                    }
                }
        )
        /*
        //Invocación del modo Kotlin
        allShopsInteractor.execute(
                success = { shops: Shops ->
                    Log.d("Shops", "Count: " + shops.count())
                },
                error = { error: String ->
                    Log.d("Shops", "Error: " + error)
                }
        )
        */
        val server = BuildConfig.MS_SERVER_URL
        Log.d("APP", server)
        /*
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
        */
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }
}
