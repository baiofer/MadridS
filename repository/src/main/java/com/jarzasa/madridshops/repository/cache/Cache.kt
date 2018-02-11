package com.jarzasa.madridshops.repository.cache

import com.jarzasa.madridshops.repository.SuccessClosure
import com.jarzasa.madridshops.repository.SuccessClosureActivity
import com.jarzasa.madridshops.repository.model.ActivityEntity
import com.jarzasa.madridshops.repository.model.ShopEntity
import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

internal interface Cache<T> {
    fun getAllShops(success: SuccessClosure, error: ErrorClosure)
    fun getAllActivities(success: SuccessClosureActivity, error: ErrorClosure)
    fun saveAllShops(saveItems: List<ShopEntity>, success: CodeClosure, error: ErrorClosure)
    fun saveAllActivities(saveItems: List<ActivityEntity>, success: CodeClosure, error: ErrorClosure)
    fun deleteAll(success: CodeClosure, error: ErrorClosure)
}