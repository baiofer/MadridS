package com.jarzasa.madridshops.repository.network

import com.jarzasa.madridshops.repository.ErrorCompletion
import com.jarzasa.madridshops.repository.SuccessCompletion

interface GetJsonManager {
    fun execute(url: String,
                success: SuccessCompletion<String>,
                error: ErrorCompletion)
}
