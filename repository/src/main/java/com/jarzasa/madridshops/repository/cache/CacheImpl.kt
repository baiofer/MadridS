package com.jarzasa.madridshops.repository.cache

import android.content.Context
import com.jarzasa.madridshops.repository.db.DBHelper
import com.jarzasa.madridshops.repository.db.build
import com.jarzasa.madridshops.repository.db.dao.ActivityDAO
import com.jarzasa.madridshops.repository.db.dao.ShopDAO
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

    private fun cacheShopsDBHelper(): DBHelper {
        return build(weakContext.get()!!, BuildConfig.DB_SHOPS_NAME, 1)
    }

    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure) {
        Thread(Runnable {
            val successDeleting = ActivityDAO(cacheActivitiesDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) success() else error("Error deleting all Activities")
            })
        }).run()
    }

    private fun cacheActivitiesDBHelper(): DBHelper {
        return build(weakContext.get()!!, BuildConfig.DB_ACTIVITIES_NAME, 1)
    }
}