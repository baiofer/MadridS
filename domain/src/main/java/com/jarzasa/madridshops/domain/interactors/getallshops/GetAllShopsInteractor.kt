package com.jarzasa.madridshops.domain.interactors.getallshops

import com.jarzasa.madridshops.domain.interactors.ErrorCompletion
import com.jarzasa.madridshops.domain.interactors.SuccessCompletion
import com.jarzasa.madridshops.domain.model.Shops

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>,
                error: ErrorCompletion)
}