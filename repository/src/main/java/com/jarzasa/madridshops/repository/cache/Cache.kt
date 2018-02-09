package com.jarzasa.madridshops.repository.cache

import com.jarzasa.madridshops.utils.CodeClosure
import com.jarzasa.madridshops.utils.ErrorClosure

internal interface Cache<T> {
    fun deleteAll(success: CodeClosure, error: ErrorClosure)
}