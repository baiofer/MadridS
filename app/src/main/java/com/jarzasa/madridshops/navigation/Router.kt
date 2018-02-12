package com.jarzasa.madridshops.navigation

import android.content.Intent
import com.jarzasa.madridshops.activity.ActivitiesActivity
import com.jarzasa.madridshops.activity.EntranceActivity
import com.jarzasa.madridshops.activity.ShopsActivity
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.domain.model.Shop

class Router {

    fun navigateFromEntranceToShops(currentActivity: EntranceActivity) {
        val intent = Intent(currentActivity, ShopsActivity::class.java)
        currentActivity.startActivity(intent)
    }

    fun navigateFromEntranceToActivities(currentActivity: EntranceActivity) {
        val intent = Intent(currentActivity, ActivitiesActivity::class.java)
        currentActivity.startActivity(intent)
    }

    fun navigateFromShopsToShopDetail(currentActivity: ShopsActivity, shop: Shop) {

    }

    fun navigateFromActivitiesToActivityDetail(currentActivity: ActivitiesActivity, activity: Activity) {

    }
}