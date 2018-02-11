package com.jarzasa.madridshops.repository

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

interface Repository {
    fun getAllShops(success: SuccessClosure, error: ErrorClosure)
    fun getAllActivities(success: SuccessClosureActivity, error: ErrorClosure)
    fun deleteAllShops(success: CodeClosure, error: ErrorClosure)
    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure)
}