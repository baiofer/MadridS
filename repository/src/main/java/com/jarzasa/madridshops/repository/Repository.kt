package com.jarzasa.madridshops.repository

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

interface Repository {
    fun deleteAllShops(success: CodeClosure, error: ErrorClosure)
    fun deleteAllActivities(success: CodeClosure, error: ErrorClosure)
}