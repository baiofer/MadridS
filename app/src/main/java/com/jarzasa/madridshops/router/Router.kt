package com.jarzasa.madridshops.router

import android.content.Intent
import com.jarzasa.madridshops.MadridShopsApp
import com.jarzasa.madridshops.activity.*
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.utils.INTENT_ACTIVITY_DETAIL
import com.jarzasa.madridshops.utils.INTENT_SHOP_DETAIL

class Router {

    fun navigateFromMadridShopsAppToEntrance(currentActivity: MadridShopsApp) {
        val intent = Intent(currentActivity, EntranceActivity::class.java)
        currentActivity.startActivity(intent)
    }

    fun navigateFromEntranceToShops(currentActivity: EntranceActivity) {
        val intent = Intent(currentActivity, ShopsActivity::class.java)
        currentActivity.startActivity(intent)
    }

    fun navigateFromEntranceToActivities(currentActivity: EntranceActivity) {
        val intent = Intent(currentActivity, ActivitiesActivity::class.java)
        currentActivity.startActivity(intent)
    }

    fun navigateFromShopsToShopDetail(currentActivity: ShopsActivity, shop: Shop) {
        val intent = Intent(currentActivity, ShopDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, shop)
        currentActivity.startActivity(intent)
    }

    fun navigateFromActivitiesToActivityDetail(currentActivity: ActivitiesActivity, activity: Activity) {
        val intent = Intent(currentActivity, DetailActivityActivity::class.java)
        intent.putExtra(INTENT_ACTIVITY_DETAIL, activity)
        currentActivity.startActivity(intent)
    }
}