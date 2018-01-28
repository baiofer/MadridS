package com.jarzasa.madridshops.domain.interactors

import com.jarzasa.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>,
                error: ErrorCompletion)
}