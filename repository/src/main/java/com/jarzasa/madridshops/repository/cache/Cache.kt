package com.jarzasa.madridshops.repository.cache

import com.jarzasa.madridshops.repository.SuccessClosure
import com.jarzasa.madridshops.repository.SuccessClosureActivity
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

internal interface Cache<T> {
    //Get
    fun getAllShops(success: SuccessClosure, error: ErrorClosure)
    fun getAllActivities(success: SuccessClosureActivity, error: ErrorClosure)
    //Save
    fun saveAllShops(shops: List<ShopEntity>, success: CodeClosure, error: ErrorClosure)
    fun saveAllActivities(activities: List<ActivityEntity>, success: CodeClosure, error: ErrorClosure)
    //Delete
    fun deleteAllShops(success: CodeClosure, error: ErrorClosure)
    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure)
}