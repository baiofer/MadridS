package com.jarzasa.madridshops.repository.network

import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
}
