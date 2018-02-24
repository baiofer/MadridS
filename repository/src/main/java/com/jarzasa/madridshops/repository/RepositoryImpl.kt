package com.jarzasa.madridshops.repository

import android.content.Context
import android.util.Log
import com.jarzasa.madridshops.repository.cache.Cache
import com.jarzasa.madridshops.repository.cache.CacheImpl
import com.jarzasa.madridshops.repository.model.ActivitiesResponseEntity
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.repository.model.ShopsResponseEntity
import com.jarzasa.madridshops.repository.network.GetJsonManager
import com.jarzasa.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.jarzasa.madridshops.repository.network.json.JsonEntitiesParser
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure
import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context) : Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cacheShops: Cache<ShopEntity> = CacheImpl<ShopEntity>(weakContext.get()!!)
    private val cacheActivities: Cache<ActivityEntity> = CacheImpl<ActivityEntity>(weakContext.get()!!)

    override fun getAllShops(success: SuccessClosure, error: ErrorClosure) {
        cacheShops.getAllShops(
                success = {
                    //if there is shops in cache --> return them
                    success(it)
                }, error = {
                    //if no shops in cache --> network
                    //Log.d("SHOPS", "No shops in cache")
                    populateCacheShops(success, error)
                })
    }

    fun populateCacheShops(success: SuccessClosure, error: ErrorClosure) {
        //perform networkrequest
        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.URL_SHOPS,
                success = object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        //parse result
                        val parser = JsonEntitiesParser()
                        val responseEntity = parser.parse<ShopsResponseEntity>(e)
                        //store result in cache
                        cacheShops.saveAllShops(responseEntity.result,
                                success = {
                                    success(responseEntity.result)
                                }, error = {
                                    error("Error getting shops from network")
                                })
                    }
                }, error = object: ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                    }
                }
        )
    }

    override fun getAllActivities(success: SuccessClosureActivity, error: ErrorClosure) {
        cacheActivities.getAllActivities(
                success = {
                    //if there is shops in cache --> return them
                    success(it)
                }, error = {
            //if no shops in cache --> network
            populateCacheActivities(success, error)
        })
    }

    fun populateCacheActivities(success: SuccessClosureActivity, error: ErrorClosure) {
        //perform networkrequest
        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.URL_ACTIVITIES,
                success = object: SuccessCompletion<String> {
                    override fun successCompletion(e: String) {
                        //parse result
                        val parser = JsonEntitiesParser()
                        val responseEntity = parser.parse<ActivitiesResponseEntity>(e)
                        //store result in cache
                        cacheActivities.saveAllActivities(responseEntity.result,
                                success = {
                                    success(responseEntity.result)
                                }, error = {
                                    error("Error geting shops from network")
                                }
                        )
                    }
                }, error = object: ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })

    }

    override fun deleteAllShops(success: CodeClosure, error: ErrorClosure) {
        val cache: Cache<ShopEntity> = CacheImpl<ShopEntity>(weakContext.get()!!)
        cache.deleteAllShops(success, error)
    }

    override fun deleteAllActivities(success: CodeClosure, error: ErrorClosure) {
        val cache: Cache<ActivityEntity> = CacheImpl<ActivityEntity>(weakContext.get()!!)
        cache.deleteAllActivities(success, error)
    }
}