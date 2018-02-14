package com.jarzasa.madridshops.navigation

import android.content.Intent
import com.jarzasa.madridshops.activity.*
import com.jarzasa.madridshops.domain.model.Activities
import com.jarzasa.madridshops.domain.model.Activity
import com.jarzasa.madridshops.domain.model.Shop
import com.jarzasa.madridshops.domain.model.Shops
import com.jarzasa.madridshops.utils.INTENT_ACTIVITIES
import com.jarzasa.madridshops.utils.INTENT_SHOPS
import com.jarzasa.madridshops.utils.INTENT_SHOP_DETAIL

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
        val intent = Intent(currentActivity, ShopDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, shop)
        currentActivity.startActivity(intent)
    }

    fun navigateFromActivitiesToActivityDetail(currentActivity: ActivitiesActivity, activity: Activity) {
        val intent = Intent(currentActivity, DetailActivityActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL, activity)
        currentActivity.startActivity(intent)
    }
}