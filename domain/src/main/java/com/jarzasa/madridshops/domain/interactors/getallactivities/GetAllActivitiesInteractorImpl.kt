package com.jarzasa.madridshops.domain.interactors.getallactivities

import android.content.Context
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.repository.Repository
import com.jarzasa.madridshops.repository.RepositoryImpl
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import java.lang.ref.WeakReference

class GetAllActivitiesInteractorImpl(context: Context) : GetAllActivitiesInteractor {

    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        repository.getAllActivities(
                success = {
                    val activities: Activities = entityMapper(it)
                    success.successCompletion(activities)
                }, error = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Activities {
        val tempList = ArrayList<Activity>()
        list.forEach {
            val activity = Activity(it.id.toInt(),
                    it.name,
                    it.description_es,
                    it.gps_lat.toFloat(),
                    it.gps_lon.toFloat(),
                    it.img,
                    it.logo_img,
                    it.opening_hours_es,
                    it.address)
            tempList.add(activity)
        }
        val activities = Activities(tempList)
        return  activities
    }
}