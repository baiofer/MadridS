package com.jarzasa.madridshops.domain.interactors.getallshops

import com.jarzasa.madridshops.utils.ErrorCompletion
import com.jarzasa.madridshops.utils.SuccessCompletion
import com.jarzasa.madridshops.domain.model.Shops

internal interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Shops>,
                error: ErrorCompletion)
}