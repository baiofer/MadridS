package com.jarzasa.madridshops.repository

import android.content.Context
import com.jarzasa.madridshops.repository.cache.Cache
import com.jarzasa.madridshops.repository.cache.CacheImpl
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure
import java.lang.ref.WeakReference

class RepositoryImpl(context: Context) : Repository {

    val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(success: CodeClosure, error: ErrorClosure) {
        val cache: Cache<ShopEntity> = CacheImpl<ShopEntity>(weakContext.get()!!)
        cache.deleteAll(success, error)
    }

    override fun deleteAllActivities(success: CodeClosure, error: ErrorClosure) {
        val cache: Cache<ActivityEntity> = CacheImpl<ActivityEntity>(weakContext.get()!!)
        cache.deleteAll(success, error)
    }
}