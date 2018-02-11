package com.jarzasa.madridshops.repository.cache

import android.content.Context
import com.jarzasa.madridshops.repository.SuccessClosure
import com.jarzasa.madridshops.repository.SuccessClosureActivity
import com.jarzasa.madridshops.repository.db.DBHelper
import com.jarzasa.madridshops.repository.db.build
import com.jarzasa.madridshops.repository.db.dao.ActivityDAO
import com.jarzasa.madridshops.repository.db.dao.ShopDAO
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.utils.BuildConfig
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.DispatchOnMainThread
import com.jarzasa.madridshops.utils.ErrorClosure
import java.lang.ref.WeakReference

internal class CacheImpl<T>(context: Context) : Cache<T> {

    val weakContext = WeakReference<Context>(context)
    private val obj: T? = null
    val isShop: Boolean = if (obj is ShopEntity) true else false


    private fun cacheShopsDBHelper(): DBHelper {
        return build(weakContext.get()!!, BuildConfig.DB_SHOPS_NAME, 1)
    }

    private fun cacheActivitiesDBHelper(): DBHelper {
        return build(weakContext.get()!!, BuildConfig.DB_ACTIVITIES_NAME, 1)
    }

    /*
     * getAll functions
     */
    override fun getAllShops(success: SuccessClosure, error: ErrorClosure) {
        Thread(Runnable {
            val shops = ShopDAO(cacheShopsDBHelper()).query()

            DispatchOnMainThread(Runnable {
                if (shops.count() > 0) success(shops) else error("No shops. Table SHOPS is empty")
            })
        }).run()
    }

    override fun getAllActivities(success: SuccessClosureActivity, error: ErrorClosure) {
        Thread(Runnable {
            val activities = ActivityDAO(cacheActivitiesDBHelper()).query()

            DispatchOnMainThread(Runnable {
                if (activities.count() > 0) success(activities) else error("No activities. Table ACTIVITIES is empty")
            })
        }).run()
    }

    /*
     * saveAll function
     */
    override fun saveAllShops(shops: List<ShopEntity>, success: CodeClosure, error: ErrorClosure) {
        Thread(Runnable {
            try {
                shops.forEach { ShopDAO(cacheShopsDBHelper()).insert(it) }
                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting shops")
                })
            }
        }).run()
    }

    override fun saveAllActivities(activities: List<ActivityEntity>, success: CodeClosure, error: ErrorClosure) {
        Thread(Runnable {
            try {
                activities.forEach { ActivityDAO(cacheActivitiesDBHelper()).insert(it) }
                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch(e: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting activities")
                })
            }
        }).run()
    }

    /*
     * deleteAll function
     */

    override fun deleteAll(success: CodeClosure, error: ErrorClosure) {
        if (isShop) {
            deleteAllShops(success, error)
        } else {
            deleteAllActivities(success, error)
        }
    }

    fun deleteAllShops(success: CodeClosure, error: ErrorClosure) {
        Thread(Runnable {
            val successDeleting = ShopDAO(cacheShopsDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) success() else error("Error deleting all Shops")
            })
        }).run()
    }

    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure) {
        Thread(Runnable {
            val successDeleting = ActivityDAO(cacheActivitiesDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) success() else error("Error deleting all Activities")
            })
        }).run()
    }

}